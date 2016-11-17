SUMMARY = "Python HTTP library with thread-safe connection pooling, file post support, sanity friendly, and more"
HOMEPAGE = "https://github.com/shazow/urllib3"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ea114851ad9a8c311aac8728a681a067"

SRC_URI[md5sum] = "4aa7c6c310cd938683e9b1831e110bac"
SRC_URI[sha256sum] = "87f362d061184191d07ced0b947d8526f3e1884f21b0fe97fab313dedcb895be"

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-certifi \
    ${PYTHON_PN}-cryptography \
    ${PYTHON_PN}-email \
    ${PYTHON_PN}-idna \
    ${PYTHON_PN}-netclient \
    ${PYTHON_PN}-pyopenssl \
    ${PYTHON_PN}-threading \
"

BBCLASSEXTEND = "native nativesdk"
