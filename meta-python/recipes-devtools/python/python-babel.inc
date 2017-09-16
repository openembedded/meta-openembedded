DESCRIPTION = "A collection of tools for internationalizing Python applications"
HOMEPAGE = "http://babel.edgewall.org/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6eeaabc92cfc2d03f53e789324d7292"

SRC_URI[md5sum] = "03da8249c2684abcbd208a8d0f979939"
SRC_URI[sha256sum] = "754177ee7481b6fac1bf84edeeb6338ab51640984e97e4083657d384b1c8830d"

PYPI_PACKAGE = "Babel"

inherit pypi

CLEANBROKEN = "1"

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-numbers \
    ${PYTHON_PN}-pickle \
    ${PYTHON_PN}-pytz \
    ${PYTHON_PN}-threading \
"
