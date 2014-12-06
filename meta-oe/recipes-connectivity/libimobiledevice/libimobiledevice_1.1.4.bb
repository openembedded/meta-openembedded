SUMMARY = "A protocol library to access an iPhone or iPod Touch in Linux"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "\
    file://COPYING;md5=ebb5c50ab7cab4baeffba14977030c07 \
    file://COPYING.LESSER;md5=6ab17b41640564434dda85c06b7124f7 \
"

#| configure:17888: checking for native large file support
#| configure:17891: error: in `/home/jenkins/oe/shr-core-branches/shr-core/tmp-eglibc/work/core2-64-oe-linux/libimobiledevice/1.1.4-r0/libimobiledevice-1.1.4':
#| configure:17893: error: cannot run test program while cross compiling
PNBLACKLIST[libimobiledevice] ?= "cannot run test program while cross compiling"

HOMEPAGE ="http://www.libimobiledevice.org/"

DEPENDS = "libplist usbmuxd libtasn1 gnutls libgcrypt"

SRC_URI = " \
    http://www.libimobiledevice.org/downloads/libimobiledevice-${PV}.tar.bz2 \
"

SRC_URI[md5sum] = "3f28cbc6a2e30d34685049c0abde5183"
SRC_URI[sha256sum] = "67499cfaa6172f566ee6b0783605acffe484fb7ddc3b09881ab7ac58667ee5b8"

inherit autotools pkgconfig

EXTRA_OECONF = " --without-cython "
