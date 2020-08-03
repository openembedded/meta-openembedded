SUMMARY = "A Python utility / library to sort Python imports."
HOMEPAGE = "https://pypi.python.org/pypi/isort"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=6;endline=6;md5=8227180126797a0148f94f483f3e1489"

SRC_URI[md5sum] = "2e501eacbf110d1e2c524590b183d339"
SRC_URI[sha256sum] = "96b27045e3187b9bdde001143b79f9b10a462f372bff7062302818013b6c86f3"

inherit pypi setuptools3

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-shell \
"

BBCLASSEXTEND = "native nativesdk"
