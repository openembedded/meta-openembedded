DESCRIPTION = "libusb-0 compatibility library using libusb-1"
HOMEPAGE = "http://libusb.sf.net"
SECTION = "libs"
LICENSE = "LGPLv2.1"
PROVIDES = "virtual/libusb0"
PE = "1"
PR = "r0"

DEPENDS = "libusb1"

SRC_URI = "${SOURCEFORGE_MIRROR}/libusb/${P}.tar.bz2;name=libusbcompat013tarbz2"
SRC_URI[libusbcompat013tarbz2.md5sum] = "570ac2ea085b80d1f74ddc7c6a93c0eb"
SRC_URI[libusbcompat013tarbz2.sha256sum] = "a590a03b6188030ee1ca1a0af55685fcde005ca807b963970f839be776031d94"

SRC_URI_append_nylon = " file://gcc-3-compatibility.patch"

inherit autotools binconfig lib_package

AUTOTOOLS_STAGE_PKGCONFIG = "1"
EXTRA_OECONF = "--disable-build-docs"
