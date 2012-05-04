DESCRIPTION = "A generic touchscreen calibration program for X.Org"
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/xinput_calibrator"
LICENSE = "MIT-X"
LIC_FILES_CHKSUM = "file://src/calibrator.cpp;endline=22;md5=998e238a7638a7446eaeb02398f691fc"
DEPENDS = "virtual/libx11 libxi"

PR = "r6"

inherit autotools systemd

SRC_URI = "http://github.com/downloads/tias/xinput_calibrator/xinput_calibrator-${PV}.tar.gz \
           file://0001-xinput_calibrator_pointercal.sh-specify-output-type-.patch \
           file://xinput-calibrator.service \
           file://xinput-calibrator-autostart.desktop \
"
SRC_URI[md5sum] = "20da0a2055a5a75962add8c6b44f60fa"
SRC_URI[sha256sum] = "baa4ddca49ec94c27ba4c715bfa26692fec1132103e927213c3169e475d3d971"

S = "${WORKDIR}/xinput_calibrator-${PV}"

# force native X11 ui as we don't have gtk+ in DEPENDS
EXTRA_OECONF += "--with-gui=x11"

do_install_append() {
        install -d ${D}${bindir}
        install -m 0755 scripts/xinput_calibrator_pointercal.sh ${D}${bindir}/xinput_calibrator_once.sh

        install -d ${D}${sysconfdir}/xdg/autostart
        install -m 0644 ${WORKDIR}/xinput-calibrator-autostart.desktop ${D}${sysconfdir}/xdg/autostart
}

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "${PN}.service"

FILES_${PN} += "${sysconfdir}/xdg/autostart"
RDEPENDS_${PN} = "xinput xterm"
RRECOMMENDS_${PN} = "pointercal-xinput"
