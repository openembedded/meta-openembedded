DESCRIPTION = "Simple Xserver Init Script (no dm)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
SECTION = "x11"
PRIORITY = "optional"
RDEPENDS_${PN} = "xserver-common (>= 1.30) xinit"
DEFAULT_PREFERENCE = "-1"
PR = "r5"

SRC_URI = "file://xserver-nodm \
           file://gplv2-license.patch \
"
S = ${WORKDIR}

inherit allarch

do_install() {
    install -d ${D}/etc
    install -d ${D}/etc/init.d
    install xserver-nodm ${D}/etc/init.d
}

inherit update-rc.d

INITSCRIPT_NAME = "xserver-nodm"
INITSCRIPT_PARAMS = "start 01 5 2 . stop 01 0 1 6 ."
INITSCRIPT_PARAMS_shr = "start 90 5 2 . stop 90 0 1 6 ."
