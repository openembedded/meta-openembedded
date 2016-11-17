DESCRIPTION = "Python HTTP for Humans."
HOMEPAGE = "http://python-requests.org"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d9bb3515869c0f426cb8441c899ae7f5"

SRC_URI[md5sum] = "d0d3e5fb81c6b4d87c08ed5cad677337"
SRC_URI[sha256sum] = "57b6c314a2c5f014dce634a0e1eeeb1707741b2e30bc7fee9c5b01fa216d57a3"

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-email \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-ndg-httpsclient \
    ${PYTHON_PN}-netserver \
    ${PYTHON_PN}-pyasn1 \
    ${PYTHON_PN}-pyopenssl \
    ${PYTHON_PN}-pysocks \
    ${PYTHON_PN}-urllib3 \
"

BBCLASSEXTEND = "native nativesdk"
