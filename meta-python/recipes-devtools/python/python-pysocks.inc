DESCRIPTION = "A Python SOCKS client module"
HOMEPAGE = "http://python-requests.org"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1d457bcffb9661b45f799d4efee72f16"

SRC_URI[md5sum] = "06524b15a3a96fe05212f12cfb87c57d"
SRC_URI[sha256sum] = "7962f4d7c76e8414ae168c677a77f19cf8926143911f7e8d37a5d4c4eb910c6f"

PYPI_PACKAGE = "PySocks"

RDEPENDS_${PN}_class-target += "\
    ${PYTHON_PN}-io \
"

BBCLASSEXTEND = "native nativesdk"
