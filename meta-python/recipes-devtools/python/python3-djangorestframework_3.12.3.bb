SUMMARY =  "djangorestframework"
DESCRIPTION = "pip3 install djangorestframework"
HOMEPAGE = "https://pypi.python.org/pypi/djangorestframework"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=7879a5a716147a784f7e524c9cf103c1"

SRC_URI[sha256sum] = "2fbbd5c28a1aaa1ad0b721b29e916797fc9aba9f9b3e7619da11c674880fc7aa"

PYPI_PACKAGE = "djangorestframework"

inherit pypi setuptools3

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-django \
"
