SUMMARY = "Test vectors for the cryptography package."
HOMEPAGE = "https://cryptography.io/"
SECTION = "devel/python"
LICENSE = "Apache-2.0 | BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8c3617db4fb6fae01f1d253ab91511e4"

DEPENDS += " \
    ${PYTHON_PN}-cryptography \
"

SRC_URI[md5sum] = "32e8bd5d5f6a9329a6753dabc8db1e5b"
SRC_URI[sha256sum] = "2de4957fdfd567d69e179d6e9ecf54a085387c953e20abf97a35a5c313aa3053"

PYPI_PACKAGE = "cryptography_vectors"

BBCLASSEXTEND = "native nativesdk"

UPSTREAM_CHECK_REGEX = ""
