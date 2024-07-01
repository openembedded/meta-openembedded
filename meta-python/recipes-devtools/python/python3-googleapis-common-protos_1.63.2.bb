DESCRIPTION = "Common protobufs used in Google APIs"
HOMEPAGE = "https://github.com/googleapis/python-api-common-protos"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit pypi setuptools3

SRC_URI[sha256sum] = "27c5abdffc4911f28101e635de1533fb4cfd2c37fbaa9174587c799fac90aa87"

RDEPENDS:${PN} += "\
    python3-grpcio \
    python3-protobuf \
"
