DESCRIPTION = "libftdi is a library to talk to FTDI chips.\
FT232BM/245BM, FT2232C/D and FT232/245R using libusb,\
including the popular bitbang mode."
HOMEPAGE = "http://www.intra2net.com/en/developer/libftdi/"
SECTION = "libs"

LICENSE = "LGPLv2.1 & GPLv2"
LIC_FILES_CHKSUM= "\
    file://COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe \
    file://COPYING.LIB;md5=db979804f025cf55aabec7129cb671ed \
"

DEPENDS = "libusb1"

SRC_URI = "http://www.intra2net.com/en/developer/${BPN}/download/${BPN}1-${PV}.tar.bz2"

SRC_URI[md5sum] = "b79a6356978aa8e69f8eecc3a720ff79"
SRC_URI[sha256sum] = "c0b1af1a13e2c6682a1d8041e5b164a1e0d90267cd378bb51e059bd62f821e21"

S = "${WORKDIR}/${BPN}1-${PV}"

PACKAGECONFIG ??= ""
PACKAGECONFIG[cpp-wrapper] = "-DFTDI_BUILD_CPP=on,-DFTDI_BUILD_CPP=off,boost"

inherit cmake binconfig pkgconfig

FILES_${PN}-dev += "${libdir}/cmake"

BBCLASSEXTEND = "native"
