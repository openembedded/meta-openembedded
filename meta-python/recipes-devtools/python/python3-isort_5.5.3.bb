SUMMARY = "A Python utility / library to sort Python imports."
HOMEPAGE = "https://pypi.python.org/pypi/isort"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=6;endline=6;md5=8227180126797a0148f94f483f3e1489"

SRC_URI[md5sum] = "5200f5b09a2a648775d0e8dbf0c0d3c8"
SRC_URI[sha256sum] = "6187a9f1ce8784cbc6d1b88790a43e6083a6302f03e9ae482acc0f232a98c843"

inherit pypi setuptools3

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-shell \
"

BBCLASSEXTEND = "native nativesdk"
