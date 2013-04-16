DESCRIPTION = "A generic touchscreen calibration program for X.Org"
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/xinput_calibrator"
LICENSE = "MIT-X"
LIC_FILES_CHKSUM = "file://src/calibrator.cpp;endline=22;md5=1bcba08f67cdb56f34021557898e4b5a"
DEPENDS = "virtual/libx11 libxi"

PV = "0.7.5+git${SRCPV}"
PR = "r6"

inherit autotools systemd

SRCREV = "c01c5af807cb4b0157b882ab07a893df9a810111"
SRC_URI = "git://github.com/tias/xinput_calibrator.git;protocol=git \
           file://xinput-calibrator.service"

S = "${WORKDIR}/git"

# force native X11 ui as we don't have gtk+ in DEPENDS
EXTRA_OECONF += "--with-gui=x11"

do_install_append() {
    install -d ${D}${bindir}
    install -m 0755 scripts/xinput_calibrator_pointercal.sh ${D}${bindir}/xinput_calibrator_once.sh

    install -d ${D}${sysconfdir}/xdg/autostart
    sed -i -e 's,^Exec=.*,Exec=${bindir}/xinput_calibrator_once.sh,' scripts/xinput_calibrator.desktop
    install -m 0644 scripts/xinput_calibrator.desktop ${D}${sysconfdir}/xdg/autostart

    if ${@base_contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/xinput-calibrator.service ${D}${systemd_unitdir}/system
    fi
}

FILES_${PN} += "${sysconfdir}/xdg/autostart"
RDEPENDS_${PN} = "xinput xterm"
RRECOMMENDS_${PN} = "pointercal-xinput"

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "${PN}.service"
