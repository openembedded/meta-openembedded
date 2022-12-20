SUMMARY = "A Python utility / library to sort Python imports."
HOMEPAGE = "https://pypi.python.org/pypi/isort"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=6;endline=6;md5=8227180126797a0148f94f483f3e1489"

SRC_URI[sha256sum] = "a8ca25fbfad0f7d5d8447a4314837298d9f6b23aed8618584c894574f626b64b"

inherit pypi python_poetry_core

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-shell \
    ${PYTHON_PN}-profile \
    ${PYTHON_PN}-numbers \
    ${PYTHON_PN}-pprint \
    ${PYTHON_PN}-difflib \
"

BBCLASSEXTEND = "native nativesdk"
