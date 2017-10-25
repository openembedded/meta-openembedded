SUMMARY = "Test vectors for the cryptography package."
HOMEPAGE = "https://cryptography.io/"
SECTION = "devel/python"
LICENSE = "Apache-2.0 | BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8c3617db4fb6fae01f1d253ab91511e4"

DEPENDS += " \
    ${PYTHON_PN}-cryptography \
"

SRC_URI[md5sum] = "54d482b0783fcda5fdd261dc0f1f84a4"
SRC_URI[sha256sum] = "1d3829bdb7b7822cee85a829fe2e0d2455d69e242186705ef1a9d4d1ab6337df"

PYPI_PACKAGE = "cryptography_vectors"

BBCLASSEXTEND = "native nativesdk"

UPSTREAM_CHECK_REGEX = ""
