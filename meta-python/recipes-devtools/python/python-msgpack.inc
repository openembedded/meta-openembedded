SUMMARY = "MessagePack (de)serializer"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=cd9523181d9d4fbf7ffca52eaa2a5751"

SRC_URI[md5sum] = "62640290ea856420d83b7f9dc1dd789d"
SRC_URI[sha256sum] = "7f5d6e59228b65c47cfdae76a4aea71f6902525f18256862cda69888a686b5f9"

PYPI_PACKAGE = "msgpack-python"
inherit pypi

RDEPENDS_${PN}_class-target += "\
    ${PYTHON_PN}-io \
"
