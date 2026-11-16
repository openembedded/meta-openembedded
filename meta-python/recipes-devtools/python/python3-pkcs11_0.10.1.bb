SUMMARY = "PKCS#11 (Cryptoki) support for Python"
DESCRIPTION = "A high level, “more Pythonic” interface to the PKCS#11 (Cryptoki) standard to support HSM and Smartcard devices in Python."
HOMEPAGE = "https://pypi.org/project/python-pkcs11/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f68bda54505b4002e6ec86e08125ef79"

SRC_URI[sha256sum] = "e0d7ccede3b3145a678d7c8c59b3c64dcebeb77c9146ad3394a4b1309c4ab2a2"

PYPI_PACKAGE = "python-pkcs11"

inherit pypi python_setuptools_build_meta cython

BBCLASSEXTEND = "native"

DEPENDS += " \
    python3-setuptools-scm-native \
    python3 \
"

RDEPENDS:${PN} += " \
    python3-asn1crypto \
    python3-cached-property \
"
