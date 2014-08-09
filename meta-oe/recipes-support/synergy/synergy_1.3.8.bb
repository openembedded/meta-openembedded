SUMMARY = "Synergy - control multiple computers with one keyboard and mouse"
HOMEPAGE = "http://synergy.googlecode.com"
LIC_FILES_CHKSUM = "file://COPYING;md5=9772a11e3569985855e2ce450e56f991"
LICENSE = "GPL-2.0"
SECTION = "x11/utils"

DEPENDS = "libx11 libxtst libxinerama"

SRC_URI = "http://synergy.googlecode.com/files/synergy-${PV}-Source.tar.gz"

SRC_URI[md5sum] = "3534c65ecfa6e47d7899c57975442f03"
SRC_URI[sha256sum] = "0afc83e4ed0b46ed497d4229b2b2854e8d3c581a112f4da05110943edbfacc03"

S = "${WORKDIR}/${PN}-${PV}-Source"

inherit cmake

do_install() {
    install -d ${D}/usr/bin
    install -m 0755 ${S}/bin/synergy* ${D}/usr/bin/
}

