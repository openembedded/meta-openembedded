DESCRIPTION = "libftdi is a library to talk to FTDI chips.\
FT232BM/245BM, FT2232C/D and FT232/245R using libusb,\
including the popular bitbang mode."
HOMEPAGE = "http://www.intra2net.com/en/developer/libftdi/"
SECTION = "libs"

PR = "r1"

LICENSE = "LGPLv2.1 GPLv2"
LIC_FILES_CHKSUM= "file://COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe \
                   file://COPYING.LIB;md5=db979804f025cf55aabec7129cb671ed \
"

PNBLACKLIST[libftdi] = "BROKEN: needs to be updated to detect libusb with pkg-config instead of libusb-config"

DEPENDS = "virtual/libusb0"
DEPENDS_virtclass-native = "virtual/libusb0-native"

SRC_URI = "http://www.intra2net.com/en/developer/libftdi/download/libftdi-${PV}.tar.gz \
           file://libtool-m4.patch \
"
SRC_URI[md5sum] = "e6e25f33b4327b1b7aa1156947da45f3"
SRC_URI[sha256sum] = "567c9d2c42d92fc401c5aba2deed45ffb2433990984e816bcdf31e441aef06be"

PACKAGECONFIG ??= ""
PACKAGECONFIG[cpp-wrapper] = "--enable-libftdipp,--disable-libftdipp,boost"

inherit autotools binconfig pkgconfig

BBCLASSEXTEND = "native"

