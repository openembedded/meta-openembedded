DEPENDS_append = " systemd-systemctl-native"

systemd_postinst() {
OPTS=""

if [ -n "$D" ]; then
    OPTS="--root=$D"
fi

systemctl $OPTS enable ${SYSTEMD_SERVICE}

if [ -z "$D" ]; then
    systemctl start ${SYSTEMD_SERVICE}
fi
}

systemd_prerm() {
systemctl stop ${SYSTEMD_SERVICE}
systemctl disable ${SYSTEMD_SERVICE}
}

def systemd_after_parse(d):
	def systemd_check_vars():
		if d.getVar('BB_WORKERCONTEXT', True) is not None:
			return

		bb_filename = d.getVar('FILE')
		packages = d.getVar('PACKAGES', 1)

		# check SYSTEMD_PACKAGES
		systemd_pkgs = d.getVar('SYSTEMD_PACKAGES', 1) or ""
		if systemd_pkgs == "":
			raise bb.build.FuncFailed, "\n\n%s inherits systemd but doesn't set SYSTEMD_PACKAGES" % bb_filename
		for pkg_systemd in systemd_pkgs.split():
			if pkg_systemd.find("-systemd") == -1:
				if pkg_systemd != d.getVar('PN', 1):
					raise bb.build.FuncFailed, \
						"\n\n%s: %s in SYSTEMD_PACKAGES does not match <existing-package>-systemd or ${PN} (deprecated)" % \
						(bb_filename, pkg_systemd)
				else:
					# Only complain if recipe lacks native systemd support
					native_systemd_support = d.getVar('NATIVE_SYSTEMD_SUPPORT', 1) or ""
					if native_systemd_support == "":
						bb.warn("%s: it is recommended to set SYSTEMD_PACKAGES as <existing-package>-systemd" % bb_filename)
			else:
				pkg_systemd_base = pkg_systemd.replace('-systemd', '')
				if pkg_systemd_base not in packages:
					raise bb.build.FuncFailed, \
						"\n\n%s: %s in SYSTEMD_PACKAGES does not match <existing-package>-systemd or ${PN} (deprecated)" % \
						( bb_filename, pkg_systemd)

		# check SYSTEMD_SERVICE
		for pkg_systemd in systemd_pkgs.split():
			service_pkg = 'SYSTEMD_SERVICE' + "_" + pkg_systemd
			systemd_services = d.getVar(service_pkg, 1) or d.getVar('SYSTEMD_SERVICE', 1) or ""

	# prepend systemd-packages not already included
	def systemd_create_package(pkg_systemd):
		packages = d.getVar('PACKAGES', 1)
		if not pkg_systemd in packages:
			packages = "%s %s" % (pkg_systemd, packages)
			d.setVar('PACKAGES', packages)


	bpn = d.getVar('BPN', 1)
	if bpn + "-native" != d.getVar('PN', 1) and \
		    bpn + "-cross" != d.getVar('PN', 1) and \
		    bpn + "-nativesdk" != d.getVar('PN', 1):
		systemd_check_vars()
		for pkg_systemd in d.getVar('SYSTEMD_PACKAGES', 1).split():
			systemd_create_package(pkg_systemd)


python __anonymous() {
    systemd_after_parse(d)
}

# automatically install all *.service and *.socket supplied in recipe's SRC_URI
do_install_append() {
    for service in `find ${WORKDIR} -maxdepth 1 -name '*.service' -o -name '*.socket'` ; do
	# ensure installing systemd-files only (e.g not avahi *.service)
	if grep -q '\[Unit\]' $service ; then
		install -d ${D}${systemd_unitdir}/system
		install -m 644 $service ${D}${systemd_unitdir}/system
	fi
    done
}

python populate_packages_prepend () {
	def systemd_generate_package_scripts(pkg):
		bb.debug(1, 'adding systemd calls to postinst/prerm for %s' % pkg)
		localdata = bb.data.createCopy(d)
		overrides = bb.data.getVar("OVERRIDES", localdata, 1)
		bb.data.setVar("OVERRIDES", "%s:%s" % (pkg, overrides), localdata)
		bb.data.update_data(localdata)

		"""
		systemd postinst is appended here because pkg_postinst may require to
		execute on the target. Not doing so may cause systemd postinst invoked
		twice to cause unwanted warnings.
		""" 
		postinst = bb.data.getVar('pkg_postinst', localdata, 1)
		if not postinst:
			postinst = '#!/bin/sh\n'
		postinst += bb.data.getVar('systemd_postinst', localdata, 1)
		bb.data.setVar('pkg_postinst_%s' % pkg, postinst, d)

		prerm = bb.data.getVar('pkg_prerm', localdata, 1)
		if not prerm:
			prerm = '#!/bin/sh\n'
		prerm += bb.data.getVar('systemd_prerm', localdata, 1)
		bb.data.setVar('pkg_prerm_%s' % pkg, prerm, d)

	# add files to FILES_*-systemd if existent and not already done
	def systemd_append_file(pkg_systemd, file_append):
		appended = False
		if os.path.exists('${D}' + file_append):
			var_name = "FILES_" + pkg_systemd
			files = d.getVar(var_name, 0) or ""
			if file_append not in files.split():
				d.setVar(var_name, "%s %s" % (files, file_append))
				appended = True
		return appended

	# add systemd files to FILES_*-systemd, parse for Also= and follow recursive
	def systemd_add_files_and_parse(pkg_systemd, path, service, keys):
		# avoid infinite recursion
		if systemd_append_file(pkg_systemd, path + service):
			fullpath = '${D}' + path + service
			if service.find('.socket') != -1:
				# for *.socket add *.service and *@.service
				service_base = service.replace('.socket', '')
				systemd_add_files_and_parse(pkg_systemd, path, service_base + '.service', keys)
				systemd_add_files_and_parse(pkg_systemd, path, service_base + '@.service', keys)
			for key in keys.split():
				# recurse all dependencies found in keys ('Also';'Conflicts';..) and add to files
				cmd = "grep %s %s | sed 's,%s=,,g' | tr ',' '\\n'" % (key, fullpath, key)
				pipe = os.popen(cmd, 'r')
				line = pipe.readline()
				while line:
					line = line.replace('\n', '')
					systemd_add_files_and_parse(pkg_systemd, path, line, keys)
					line = pipe.readline()
				pipe.close()

	# check service-files and call systemd_add_files_and_parse for each entry
	def systemd_check_services():
		searchpaths = '/etc/systemd/system/ /lib/systemd/system/ /usr/lib/systemd/system/'
		systemd_packages = d.getVar('SYSTEMD_PACKAGES', 1)
		has_exactly_one_service = len(systemd_packages.split()) == 1
		if has_exactly_one_service:
			systemd_services = d.getVar('SYSTEMD_SERVICE' + "_" + systemd_packages, 1) or d.getVar('SYSTEMD_SERVICE', 1)
			has_exactly_one_service = len(systemd_services.split()) == 1

		keys = 'Also' # Conflicts??
		if has_exactly_one_service:
			# single service gets also the /dev/null dummies
			keys = 'Also Conflicts'
		# scan for all in SYSTEMD_SERVICE[]
		for pkg_systemd in systemd_packages.split():
			systemd_services = d.getVar('SYSTEMD_SERVICE' + "_" + pkg_systemd, 1) or d.getVar('SYSTEMD_SERVICE', 1)
			for service in systemd_services.split():
				path_found = ''
				for path in searchpaths.split():
					if os.path.exists('${D}' + path + service):
						path_found = path
				if path_found != '':
					systemd_add_files_and_parse(pkg_systemd, path_found, service, keys)
				else:
					raise bb.build.FuncFailed, "\n\nFor package %s SYSTEMD_SERVICE-entry %s does not exist" % \
						(pkg_systemd, service)

	# *-systemd packages get RDEPENDS to systemd and their base package
	def systemd_add_rdepends(pkg_systemd):
		# RDEPENDS_${pkg_systemd} += pkg_systemd_base systemd
		rdepends = d.getVar('RDEPENDS_' + pkg_systemd, 1) or ""
		rdepends_arr = rdepends.split()
		if not 'systemd' in rdepends_arr:
			rdepends = '%s %s' % (rdepends, 'systemd')
		pkg_systemd_base = pkg_systemd.replace('-systemd', '')
		# no automatism for:
		# recipes setting rdepends themselves AND
		# not rdepending myself AND
		# avoid double entries
		if len(rdepends_arr) == 0 and pkg_systemd != '${PN}' and not pkg_systemd_base in rdepends:
			rdepends = '%s %s' % (rdepends, pkg_systemd_base)
		d.setVar('RDEPENDS_' + pkg_systemd, rdepends)


	# run all modifications once when creating package
	if os.path.exists('${D}'):
		for pkg_systemd in d.getVar('SYSTEMD_PACKAGES', 1).split():
			if d.getVar('SYSTEMD_SERVICE' + "_" + pkg_systemd, 1) and d.getVar('SYSTEMD_SERVICE' + "_" + pkg_systemd, 1).strip():
				systemd_generate_package_scripts(pkg_systemd)
				systemd_add_rdepends(pkg_systemd)
		systemd_check_services()
}
