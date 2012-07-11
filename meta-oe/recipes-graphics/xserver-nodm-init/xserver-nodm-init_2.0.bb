DESCRIPTION = "Simple Xserver Init Script (no dm)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
SECTION = "x11"

PR = "r17"

SRC_URI = "file://xserver-nodm \
           file://gplv2-license.patch \
"
S = "${WORKDIR}"

inherit allarch update-rc.d

INITSCRIPT_NAME = "xserver-nodm"
INITSCRIPT_PARAMS = "start 01 5 2 . stop 01 0 1 6 ."
INITSCRIPT_PARAMS_shr = "start 90 5 2 . stop 90 0 1 6 ."

do_install() {
	install -d ${D}${sysconfdir}/init.d
	install xserver-nodm ${D}${sysconfdir}/init.d
}

RDEPENDS_${PN} = "xserver-common (>= 1.30) xinit"
