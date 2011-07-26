DESCRIPTION = "Simple Xserver Init Script (no dm)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
SECTION = "x11"

PR = "r9"

SRC_URI = "file://xserver-nodm \
           file://xserver-nodm.service \
           file://gplv2-license.patch \
"
S = ${WORKDIR}

inherit allarch

do_install() {
	install -d ${D}${sysconfdir}/init.d
	install xserver-nodm ${D}${sysconfdir}/init.d

	install -d ${D}${base_libdir}/systemd/system
	install -m 0644 xserver-nodm.service ${D}${base_libdir}/systemd/system/
}

RDEPENDS_${PN} = "xserver-common (>= 1.30) xinit"
FILES_${PN} += "${base_libdir}/systemd/system/"

inherit update-rc.d

INITSCRIPT_NAME = "xserver-nodm"
INITSCRIPT_PARAMS = "start 01 5 2 . stop 01 0 1 6 ."
INITSCRIPT_PARAMS_shr = "start 90 5 2 . stop 90 0 1 6 ."

pkg_postinst_${PN}_append () {
    # can't do this offline
    if [ "x$D" != "x" ]; then
        exit 1
    fi

    if [ -e /bin/systemctl ] ; then
        systemctl enable xserver-nodm.service
    fi
}
