DESCRIPTION = "Google API client core library"
HOMEPAGE = "https://github.com/googleapis/python-api-core"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit pypi setuptools3

SRC_URI[sha256sum] = "7f668ffa3d5b9f3c6930407e5f5d691c05a376050a5a5fd772b9dc32e70a0c30"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-asyncio \
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-math \
"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-googleapis-common-protos \
    ${PYTHON_PN}-google-auth \
    ${PYTHON_PN}-grpcio \
    ${PYTHON_PN}-protobuf \
    ${PYTHON_PN}-pytz \
    ${PYTHON_PN}-requests \
    ${PYTHON_PN}-six \
"
