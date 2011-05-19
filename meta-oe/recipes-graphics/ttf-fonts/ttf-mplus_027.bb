require ttf.inc

DESCRIPTION = "MPlus font - TTF Edition"
HOMEPAGE = "http://dejavu.sourceforge.net/wiki/"
LICENSE = "${PN}"
PR = "r1"

SRC_URI = "http://osdn.dl.sourceforge.jp/mplus-fonts/6650/mplus-TESTFLIGHT-${PV}.tar.gz"
S = "${WORKDIR}/mplus-TESTFLIGHT-${PV}"

python populate_packages_prepend() {
	plugindir = bb.data.expand('${datadir}/fonts/ttf-mplus/', d)
	do_split_packages(d, plugindir, '^(.*)\.ttf$', 'ttf-%s', 'TTF Font %s', extra_depends = "ttf-common")
}

do_install() {
	install -d ${D}${datadir}/fonts/ttf-mplus
	install -m 0644 *.ttf ${D}${datadir}/fonts/ttf-mplus/
}

SRC_URI[md5sum] = "d1400184b51b3871e8d2fca6c50e18ae"
SRC_URI[sha256sum] = "a20b9b9b03c2a6fb1e2137d29e8a6ce06406ba1e008906ea3c474dc048dc06a6"
