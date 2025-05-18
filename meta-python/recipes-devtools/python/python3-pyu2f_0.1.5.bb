SUMMARY = "U2F host library for interacting with a U2F device over USB."
HOMEPAGE = "https://github.com/google/pyu2f/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[sha256sum] = "a3caa3a11842fc7d5746376f37195e6af5f17c0a15737538bb1cebf656fb306b"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-core \
    ${PYTHON_PN}-crypt \
    ${PYTHON_PN}-ctypes \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-netclient \
    ${PYTHON_PN}-threading \
    ${PYTHON_PN}-six \
"
