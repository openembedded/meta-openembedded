SUMMARY = "A protocol library to access an iPhone or iPod Touch in Linux"
LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = "\
    file://COPYING;md5=ebb5c50ab7cab4baeffba14977030c07 \
    file://COPYING.LESSER;md5=6ab17b41640564434dda85c06b7124f7 \
"
HOMEPAGE = "http://www.libimobiledevice.org/"

DEPENDS = "libplist usbmuxd libusbmuxd libtasn1 gnutls libgcrypt libimobiledevice-glue openssl"

PV = "1.3.0+git${SRCPV}"

SRCREV = "2eec1b9a172354c8521123a767d998b17bd2ac18"
SRC_URI = "git://github.com/libimobiledevice/libimobiledevice;protocol=https;branch=master"

S = "${WORKDIR}/git"
inherit autotools pkgconfig

EXTRA_OECONF = " --without-cython "

CFLAGS += "-D_GNU_SOURCE"
