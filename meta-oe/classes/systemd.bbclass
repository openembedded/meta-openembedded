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
if [ -z "$D" ]; then
    systemctl stop ${SYSTEMD_SERVICE}
fi
}

systemd_postrm() {
systemctl disable ${SYSTEMD_SERVICE}
}

def systemd_after_parse(d):
    if bb.data.getVar('SYSTEMD_PACKAGES', d) == None:
        if bb.data.getVar('SYSTEMD_SERVICE', d) == None:
            raise bb.build.FuncFailed, "%s inherits systemd but doesn't set SYSTEMD_SERVICE" % bb.data.getVar('FILE', d)

python __anonymous() {
    systemd_after_parse(d)
}

python populate_packages_prepend () {
	def systemd_package(pkg):
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


	pkgs = bb.data.getVar('SYSTEMD_PACKAGES', d, 1)
	if pkgs == None:
		pkgs = bb.data.getVar('PN', d, 1)
		packages = (bb.data.getVar('PACKAGES', d, 1) or "").split()
		if not pkgs in packages and packages != []:
			pkgs = packages[0]
	for pkg in pkgs.split():
		systemd_package(pkg)
}
