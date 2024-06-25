DESCRIPTION = "Common protobufs used in Google APIs"
HOMEPAGE = "https://github.com/googleapis/python-api-common-protos"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit pypi setuptools3

SRC_URI[sha256sum] = "c6442f7a0a6b2a80369457d79e6672bb7dcbaab88e0848302497e3ec80780a6a"

RDEPENDS:${PN} += "\
    python3-grpcio \
    python3-protobuf \
"
