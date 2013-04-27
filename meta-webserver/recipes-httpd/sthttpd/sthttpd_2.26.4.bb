SUMMARY = "A simple, small, portable, fast, and secure HTTP server"
DESCRIPTION = "A simple, small, portable, fast, and secure HTTP server (supported fork of thttpd)."
HOMEPAGE = "http://opensource.dyc.edu/sthttpd"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://src/thttpd.c;beginline=1;endline=26;md5=0c5762c2c34dcbe9eb18815516502872"

SRC_URI = "http://opensource.dyc.edu/pub/sthttpd/sthttpd-${PV}.tar.gz \
           file://init"

SRC_URI[md5sum] = "e645a85a97d3cb883011a35bc2211815"
SRC_URI[sha256sum] = "78e87979140cbda123c81b4051552242dbbffb5dec1a17e5f95ec4826b1eaddb"

S = "${WORKDIR}/sthttpd-${PV}"

inherit autotools update-rc.d

EXTRA_OEMAKE += "'WEBDIR=${servicedir}/www'"

do_install_append () {
    install -d ${D}${sysconfdir}/init.d
    install -c -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/thttpd
    sed -i -e 's,@@SRVDIR,${servicedir}/www,g' ${D}${sysconfdir}/init.d/thttpd
    sed -i 's!/usr/sbin/!${sbindir}/!g' ${D}${sysconfdir}/init.d/thttpd
}

INITSCRIPT_NAME = "thttpd"
INITSCRIPT_PARAMS = "defaults"

FILES_${PN} += "${servicedir}"
FILES_${PN}-dbg += "${servicedir}/www/cgi-bin/.debug"

