DESCRIPTION = "PC/SC Lite smart card framework and applications"
HOMEPAGE = "http://pcsclite.alioth.debian.org/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=c8e551349dc346258274f0007679e149"
DEPENDS = "udev"
PR = "r3"

SRC_URI = "https://alioth.debian.org/frs/download.php/3533/pcsc-lite-${PV}.tar.bz2"

SRC_URI[md5sum] = "47e7055cfc14399fdaa1b7a4aa06e5aa"
SRC_URI[sha256sum] = "41f13d552eaa2c3978fbb6f2125e81903a0767011d999052fd1a6ee03880b398"

inherit autotools

EXTRA_OECONF = " \
	--disable-libusb \
	--enable-libudev \
	--enable-usbdropdir=${libdir}/pcsc/drivers \
	"

S = "${WORKDIR}/pcsc-lite-${PV}"

PACKAGES =+ "${PN}-lib"

RRECOMMENDS_${PN} = "ccid"

FILES_${PN}-lib = "${libdir}/lib*${SOLIBS}"
