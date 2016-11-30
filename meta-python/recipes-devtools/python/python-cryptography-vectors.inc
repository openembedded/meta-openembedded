SUMMARY = "Test vectors for the cryptography package."
SECTION = "devel/python"
LICENSE = "Apache-2.0 | BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8c3617db4fb6fae01f1d253ab91511e4"

DEPENDS = " \
    ${PYTHON_PN}-cryptography \
"

SRC_URI[md5sum] = "d7d53f061366394267be444fa84cfe89"
SRC_URI[sha256sum] = "01ccb07c95d128a70732f274bd16af479bcc344e43cac745d2b9ec4ab71ff675"

PYPI_PACKAGE = "cryptography_vectors"

BBCLASSEXTEND = "native nativesdk"
