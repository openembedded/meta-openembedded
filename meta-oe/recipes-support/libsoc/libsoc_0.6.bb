SUMMARY = "libsoc is a C library for interfacing with common SoC peripherals through generic kernel interfaces"
HOMEPAGE = "https://github.com/jackmitch/libsoc"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=e0bfebea12a718922225ba987b2126a5"

inherit autotools

SRCREV = "3643cf161a4b37bfbdfd05437166c4a29ac3ed8d"
SRC_URI = "git://github.com/jackmitch/libsoc.git"

S = "${WORKDIR}/git"

RDEPENDS_${PN} = "libgcc"
