DESCRIPTION = "A small network printer daemon for embedded situations that passes the job directly to the printer"
HOMEPAGE = "http://p910nd.sourceforge.net/"
SECTION = "console/utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

PR = "r0"

inherit update-rc.d

INITSCRIPT_NAME = "p910nd"
INITSCRIPT_PARAMS = "defaults 60 "

SRC_URI = "${SOURCEFORGE_MIRROR}/p910nd/p910nd-${PV}.tar.bz2 \
           file://p910nd.init"

SRC_URI[md5sum] = "c7ac6afdf7730ac8387a8e87198d4491"
SRC_URI[sha256sum] = "7d78642c86dc247fbdef1ff85c56629dcdc6b2a457c786420299e284fffcb029"

do_compile () {
	${CC} ${LDFLAGS} -o p910nd p910nd.c
}

# The avahi stuff makes it work with bonjour printing
do_install () {
	install -D -m 0755 ${S}/p910nd ${D}${sbindir}/p910nd
	install -D -m 0644 ${S}/p910nd.conf ${D}${sysconfdir}/p910nd.conf
	install -D -m 0755 ${WORKDIR}/p910nd.init ${D}${sysconfdir}/init.d/p910nd
}
