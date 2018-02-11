SUMMARY = "MessagePack (de)serializer"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=cd9523181d9d4fbf7ffca52eaa2a5751"

SRC_URI[md5sum] = "b7759a382dc6309b35e0a0eecc1f23f1"
SRC_URI[sha256sum] = "23f688905bb9fbf00faa7346e72a72e670e68f3f5d94aeea5c123dd0e07de49c"

PYPI_PACKAGE = "msgpack-python"
inherit pypi

RDEPENDS_${PN}_class-target += "\
    ${PYTHON_PN}-io \
"
