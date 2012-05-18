DESCRIPTION = "Test suite for Linux framebuffer"

PV = "0.0"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a"

SRCREV = "85eab266b123a08098af86afdb1599947d276e32"
SRC_URI = "git://github.com/prpplague/fb-test-app.git"

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}${bindir}
	for prog in perf rect fb-test offset ; do
		install -m 0755 $prog ${D}${bindir}
	done
}

