SUMMARY = "Pytest plugin for measuring coverage."
HOMEPAGE = "https://github.com/pytest-dev/pytest-cov"
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=cbc4e25353c748c817db2daffe605e43 \
"

SRC_URI[sha256sum] = "25cc6cc0a5358204b8108ecedc51a9b57b34cc6b8c967cc2c01a4e00d8a67da2"

inherit pypi setuptools3

DEPENDS += "python3-setuptools-scm-native"
RDEPENDS:${PN} += "python3-coverage python3-pytest python3-pluggy"

PYPI_PACKAGE = "pytest_cov"

BBCLASSEXTEND = "native nativesdk"
