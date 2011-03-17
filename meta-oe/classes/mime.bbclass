DEPENDS += "shared-mime-info-native shared-mime-info"

mime_postinst() {
if [ "$1" = configure ]; then
	if [ -x ${bindir}/update-mime-database ] ; then
		echo "Updating MIME database... this may take a while."
		update-mime-database $D${datadir}/mime
	else
		echo "Missing ${bindir}/update-mime-database, update of mime database failed!"
		exit 1
	fi
fi
}

mime_postrm() {
if [ "$1" = remove ] || [ "$1" = upgrade ]; then
	if [ -x ${bindir}/update-mime-database ] ; then
		echo "Updating MIME database... this may take a while."
		update-mime-database $D${datadir}/mime
	else
		echo "Missing ${bindir}/update-mime-database, update of mime database failed!"
		exit 1
	fi
fi
}

python ppopulate_packages_append () {
	import os.path, re
	packages = bb.data.getVar('PACKAGES', d, 1).split()
	pkgdest =  bb.data.getVar('PKGDEST', d, 1)
	
	for pkg in packages:
		mime_dir = '%s/%s/usr/share/mime/packages' % (pkgdest, pkg)
		mimes = []
		mime_re = re.compile(".*\.xml$")
		if os.path.exists(mime_dir):
			for f in os.listdir(mime_dir):
				if mime_re.match(f):
					mimes.append(f)
		if mimes != []:
			bb.note("adding mime postinst and postrm scripts to %s" % pkg)
			postinst = bb.data.getVar('pkg_postinst_%s' % pkg, d, 1) or bb.data.getVar('pkg_postinst', d, 1)
			if not postinst:
				postinst = '#!/bin/sh\n'
			postinst += bb.data.getVar('mime_postinst', d, 1)
			bb.data.setVar('pkg_postinst_%s' % pkg, postinst, d)
			postrm = bb.data.getVar('pkg_postrm_%s' % pkg, d, 1) or bb.data.getVar('pkg_postrm', d, 1)
			if not postrm:
				postrm = '#!/bin/sh\n'
			postrm += bb.data.getVar('mime_postrm', d, 1)
			bb.data.setVar('pkg_postrm_%s' % pkg, postrm, d)
			bb.note("adding freedesktop-mime-info dependency to %s" % pkg)
			rdepends = explode_deps(bb.data.getVar('RDEPENDS_' + pkg, d, 0) or bb.data.getVar('RDEPENDS', d, 0) or "")
			rdepends.append("freedesktop-mime-info")
			bb.data.setVar('RDEPENDS_' + pkg, " " + " ".join(rdepends), d)
}
