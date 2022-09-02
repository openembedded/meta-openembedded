SUMMARY = "websocket client for python"
DESCRIPTION = "\
websocket-client module is WebSocket client for python. \
This provide the low level APIs for WebSocket. All APIs \
are the synchronous functions."
HOMEPAGE = "https://github.com/websocket-client/websocket-client"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b969e9612325987c823fc0737063ebc8"

SRC_URI[sha256sum] = "79d730c9776f4f112f33b10b78c8d209f23b5806d9a783e296b3813fc5add2f1"

inherit pypi setuptools3

RDEPENDS:${PN} = "\
    ${PYTHON_PN}-six \
    ${PYTHON_PN}-logging \
"
