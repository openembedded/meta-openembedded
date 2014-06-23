SUMMARY = "PC/SC Lite smart card framework and applications"
HOMEPAGE = "http://pcsclite.alioth.debian.org/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=a39d325b7d9cf2f07826a5154b16500c"
DEPENDS = "udev"

SRC_URI = "https://alioth.debian.org/frs/download.php/file/3757/pcsc-lite-${PV}.tar.bz2"
SRC_URI[md5sum] = "3e4b777231d35ce35c15c0704f936eaa"
SRC_URI[sha256sum] = "1e44049168d1ce55fd56c175f61206955254df0f385455f2a20ec7e8a0e6a77a"

PR = "r3"

inherit autotools systemd pkgconfig

EXTRA_OECONF = " \
    --disable-libusb \
    --enable-libudev \
    --enable-usbdropdir=${libdir}/pcsc/drivers \
"

S = "${WORKDIR}/pcsc-lite-${PV}"

PACKAGES =+ "${PN}-lib"

RRECOMMENDS_${PN} = "ccid"

FILES_${PN}-lib = "${libdir}/lib*${SOLIBS}"

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "pcscd.socket"
