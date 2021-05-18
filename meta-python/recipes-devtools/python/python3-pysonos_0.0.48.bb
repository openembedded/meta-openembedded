DESCRIPTION = "A SoCo fork with fixes for Home Assistant "
HOMEPAGE = "https://pypi.org/project/pysonos/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=07b0e2ca9ac77cd65cd4edf2e13367ea"

SRC_URI[sha256sum] = "69c55bf80aae77b3ea8658ee1a80b9aafc0506931614853c926830a502230fc6"

inherit pypi setuptools3

RDEPENDS_${PN} += "\
	${PYTHON_PN}-ifaddr \
	${PYTHON_PN}-requests \
	${PYTHON_PN}-xmltodict \
	"
