SUMMARY = "A Python implementation of John Gruber's Markdown."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=745aaad0c69c60039e638bff9ffc59ed"

inherit pypi setuptools3

PYPI_PACKAGE = "Markdown"
SRC_URI[md5sum] = "c7b68676df277df34342bb9a27f7fe30"
SRC_URI[sha256sum] = "c3ce9ebb035c078cac0f2036068d054e7dc34354eeecc49c173c33c96b124af6"

BBCLASSEXTEND = "native"

RDEPENDS_${PN} += "${PYTHON_PN}-logging ${PYTHON_PN}-setuptools"
