DESCRIPTION = "Common protobufs used in Google APIs"
HOMEPAGE = "https://github.com/googleapis/python-api-common-protos"
AUTHOR = "Google LLC"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit pypi setuptools3

SRC_URI[sha256sum] = "6b5ee59dc646eb61a8eb65ee1db186d3df6687c8804830024f32573298bca19b"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-grpcio \
    ${PYTHON_PN}-protobuf \
"
