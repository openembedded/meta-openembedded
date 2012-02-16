DEPENDS_append = " systemd-systemctl-native"

systemd_unitdir = "${base_libdir}/systemd"

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
if [ -z "$D" ]; then
    systemctl stop ${SYSTEMD_SERVICE}
fi
}

systemd_postrm() {
systemctl disable ${SYSTEMD_SERVICE}
}

def systemd_after_parse(d):
	def systemd_check_vars():
		bpn = d.getVar('BPN', 1)
		# not for native / only at parse time
		if d.getVar('BB_WORKERCONTEXT', True) is None and \
		bpn + "-native" != d.getVar('PN', 1) and \
		bpn + "-cross" != d.getVar('PN', 1) and \
		bpn + "-nativesdk" != d.getVar('PN', 1):
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
				if systemd_services == "":
					raise bb.build.FuncFailed, "\n\n%s inherits systemd but doesn't set SYSTEMD_SERVICE / %s" % (bb_filename, service_pkg)

	# prepend systemd-packages not already included
	def systemd_create_package(pkg_systemd):
		packages = d.getVar('PACKAGES', 1)
		if not pkg_systemd in packages:
			packages = "%s %s" % (pkg_systemd, packages)
			d.setVar('PACKAGES', packages)


	systemd_check_vars()
	for pkg_systemd in d.getVar('SYSTEMD_PACKAGES', 1).split():
		systemd_create_package(pkg_systemd)


python __anonymous() {
    systemd_after_parse(d)
}

python populate_packages_prepend () {
	def systemd_generate_package_scripts(pkg):
		bb.debug(1, 'adding systemd calls to postinst/postrm for %s' % pkg)
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

		postrm = bb.data.getVar('pkg_postrm', localdata, 1)
		if not postrm:
			postrm = '#!/bin/sh\n'
		postrm += bb.data.getVar('systemd_postrm', localdata, 1)
		bb.data.setVar('pkg_postrm_%s' % pkg, postrm, d)

		rdepends = explode_deps(bb.data.getVar('RDEPENDS_' + pkg, d, 0) or bb.data.getVar('RDEPENDS', d, 0) or "")
		rdepends.append("systemd")
		bb.data.setVar('RDEPENDS_' + pkg, " " + " ".join(rdepends), d)


	for pkg_systemd in d.getVar('SYSTEMD_PACKAGES', 1).split():
		systemd_generate_package_scripts(pkg_systemd)
}
