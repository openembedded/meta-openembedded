SUMMARY = "MQTT version 3.1/3.1.1 client library"
LICENSE = "EPL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=eb48c6ee2cb9f5b8b9fe75e6f817bdfc"
SRCNAME = "paho-mqtt"

inherit pypi
SRC_URI[md5sum] = "4bd192ea24e7aa347f6d240101ef82f6"
SRC_URI[sha256sum] = "0fd746d8650563290f1bd0fec01e74cb57c3ab7406bdb58e5d9252bb5fa5ca44"

RDEPENDS_${PN} = "\
               ${PYTHON_PN}-math \
               ${PYTHON_PN}-io \
               ${PYTHON_PN}-threading \
"
