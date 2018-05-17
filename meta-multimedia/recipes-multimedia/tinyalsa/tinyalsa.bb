DESCRIPTION = "TinyALSA is a small library to interface with ALSA in \
the Linux kernel. It is a lightweight alternative to libasound."
HOMEPAGE = "https://github.com/tinyalsa/tinyalsa"
SECTION = "libs/multimedia"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://NOTICE;md5=dbdefe400d894b510a9de14813181d0b"

SRCREV = "67b9210d344c34e8d1aa0cfe638abce71c5221ca"
SRC_URI = "git://github.com/tinyalsa/tinyalsa"
PV = "1.1.1+git${SRCPV}"

S = "${WORKDIR}/git"

inherit cmake

PACKAGES =+ "${PN}-tools"
FILES_${PN}-tools = "${bindir}/*"
