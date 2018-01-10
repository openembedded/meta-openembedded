SUMMARY = "MessagePack (de)serializer"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=cd9523181d9d4fbf7ffca52eaa2a5751"

SRC_URI[md5sum] = "29ef230aab635b7d3daaa349c735a1a0"
SRC_URI[sha256sum] = "69aa1eb0e13be1d3bd495ca937eae66df4431126f5cfd5491dc40370e5644853"

PYPI_PACKAGE = "msgpack-python"
inherit pypi

RDEPENDS_${PN}_class-target += "\
    ${PYTHON_PN}-io \
"
