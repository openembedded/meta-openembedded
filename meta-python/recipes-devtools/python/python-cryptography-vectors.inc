SUMMARY = "Test vectors for the cryptography package."
SECTION = "devel/python"
LICENSE = "Apache-2.0 | BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8c3617db4fb6fae01f1d253ab91511e4"

DEPENDS += " \
    ${PYTHON_PN}-cryptography \
"

SRC_URI[md5sum] = "e6ee71f1cc9950e3df026bef69e5fdcb"
SRC_URI[sha256sum] = "2fd61facea08800ca98ac923f6d02f48a7ae6648025b29cdeb51987c1532add6"

PYPI_PACKAGE = "cryptography_vectors"

BBCLASSEXTEND = "native nativesdk"

UPSTREAM_CHECK_REGEX = ""
