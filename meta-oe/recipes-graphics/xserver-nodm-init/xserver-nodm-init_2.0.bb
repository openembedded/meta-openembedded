DESCRIPTION = "Simple Xserver Init Script (no dm)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
SECTION = "x11"

PR = "r12"

SRC_URI = "file://xserver-nodm \
           file://xserver-nodm.service \
           file://gplv2-license.patch \
"
S = ${WORKDIR}

inherit allarch

inherit update-rc.d systemd

INITSCRIPT_NAME = "xserver-nodm"
INITSCRIPT_PARAMS = "start 01 5 2 . stop 01 0 1 6 ."
INITSCRIPT_PARAMS_shr = "start 90 5 2 . stop 90 0 1 6 ."

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "xserver-nodm.service"

do_install() {
	install -d ${D}${sysconfdir}/init.d
	install xserver-nodm ${D}${sysconfdir}/init.d

	install -d ${D}${base_libdir}/systemd/system
	install -m 0644 xserver-nodm.service ${D}${base_libdir}/systemd/system/
}

PACKAGES += "${PN}-systemd"

RDEPENDS_${PN} = "xserver-common (>= 1.30) xinit"

FILES_${PN}-systemd += "${base_libdir}/systemd/system/"
RDEPENDS_${PN}-systemd += "xserver-common (>= 1.30) xinit"
