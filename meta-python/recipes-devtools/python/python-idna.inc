SUMMARY = "Internationalised Domain Names in Applications"
HOMEPAGE = "https://github.com/kjd/idna"
LICENSE = "BSD-3-Clause & Python-2.0 & Unicode"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=c9ba5acd829579492fd5eb306c7a0565"

SRC_URI[md5sum] = "b27328914784bf3e4f6fae16f4b75ba9"
SRC_URI[sha256sum] = "2a07165f6288f4b920aa8ab4357c1e59073c5d62e048a400510982769e039bd9"

RDEPENDS_${PN}_class-target = "\
    ${PYTHON_PN}-codecs \
"

BBCLASSEXTEND = "native nativesdk"
