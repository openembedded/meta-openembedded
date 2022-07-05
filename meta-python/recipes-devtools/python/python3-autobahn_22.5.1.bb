DESCRIPTION = "WebSocket client & server library, WAMP real-time framework"
HOMEPAGE = "http://crossbar.io/autobahn"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=97c0bda20ad1d845c6369c0e47a1cd98"

SRC_URI[sha256sum] = "34aa5569b0b4419f8c277792c600dc2518c4930928c74e2279ef8b88d8b8a3ea"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-twisted \
    ${PYTHON_PN}-zopeinterface \
    ${PYTHON_PN}-py-ubjson \
    ${PYTHON_PN}-cbor2 \
    ${PYTHON_PN}-u-msgpack-python \
    ${PYTHON_PN}-lz4 \
    ${PYTHON_PN}-snappy \
    ${PYTHON_PN}-pyopenssl \
    ${PYTHON_PN}-txaio \
    ${PYTHON_PN}-six \
"

BBCLASSEXTEND = "native nativesdk"
