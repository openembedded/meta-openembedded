SUMMARY = "websocket client for python"
DESCRIPTION = "\
websocket-client module is WebSocket client for python. \
This provide the low level APIs for WebSocket. All APIs \
are the synchronous functions."
HOMEPAGE = "https://github.com/websocket-client/websocket-client"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c96ca6c1de8adc025adfada81d06fba5"

SRC_URI[sha256sum] = "5051b38a2f4c27fbd7ca077ebb23ec6965a626ded5a95637f36be1b35b6c4f81"

inherit pypi setuptools3

RDEPENDS_${PN} = "\
    ${PYTHON_PN}-six \
"
