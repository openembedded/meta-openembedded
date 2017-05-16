require recipes-graphics/xorg-driver/xorg-driver-input.inc
SUMMARY = "X.Org X server -- tslib input driver"
DEPENDS += "tslib"
RRECOMMENDS_${PN} += "tslib-calibrate"
RSUGGESTS_${PN} += "hal"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=f1524518264f7776a9707c19c8affbbf"

PR = "${INC_PR}.1"

SRC_URI = "http://www.pengutronix.de/software/xf86-input-tslib/download/xf86-input-tslib-${PV}.tar.bz2 \
           file://10-x11-input-tslib.fdi \
           file://99-xf86-input-tslib.rules \
"

SRC_URI[md5sum] = "dea5f1f7713f69fb37353320fd4e67a8"
SRC_URI[sha256sum] = "6f23cc9702b0ae16086d364b275335c094efbf6acde57f8a030e4db5b9aece03"

do_configure_prepend() {
    rm -rf ${S}/m4/ || true
}
do_install_append() {
    install -d ${D}/${datadir}/hal/fdi/policy/20thirdparty
    install -m 0644 ${WORKDIR}/10-x11-input-tslib.fdi ${D}/${datadir}/hal/fdi/policy/20thirdparty
    install -d ${D}${nonarch_base_libdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/99-xf86-input-tslib.rules ${D}${nonarch_base_libdir}/udev/rules.d/
}

FILES_${PN} += "${datadir}/hal ${nonarch_base_libdir}/udev"
