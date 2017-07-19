SUMMARY = "MQTT version 3.1/3.1.1 client library"
LICENSE = "EPL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=eb48c6ee2cb9f5b8b9fe75e6f817bdfc"
SRCNAME = "paho-mqtt"

inherit pypi
SRC_URI[md5sum] = "b9338236e2836e8579ef140956189cc4"
SRC_URI[sha256sum] = "2c9ef5494cfc1e41a9fff6544c5a2cd59ea5d401d9119a06ecf7fad6a2ffeb93"

RDEPENDS_${PN} = "\
               ${PYTHON_PN}-math \
               ${PYTHON_PN}-io \
               ${PYTHON_PN}-threading \
"
