SUMMARY = "libsoc is a C library for interfacing with common SoC peripherals through generic kernel interfaces"
HOMEPAGE = "https://github.com/jackmitch/libsoc"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=e0bfebea12a718922225ba987b2126a5"

inherit autotools

SRCREV = "f6827b7b5c581b586d3bcc4813d418101ae1e85b"
SRC_URI = "git://github.com/jackmitch/libsoc.git"

S = "${WORKDIR}/git"

RDEPENDS_${PN} = "libgcc"
