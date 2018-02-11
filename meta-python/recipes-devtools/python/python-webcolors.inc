SUMMARY = "Simple Python module for working with HTML/CSS color definitions."
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=197add016087e6884a012b0f185d44ad"

SRC_URI[md5sum] = "d7a17135f8b8374a75c2f1d08253c026"
SRC_URI[sha256sum] = "da3cb551060f46f417341b041b36d329d699c125a13a4412dde5c2e310d63344"

RDEPENDS_${PN}_class-target = "\
    ${PYTHON_PN}-stringold \
"

BBCLASSEXTEND = "native nativesdk"
