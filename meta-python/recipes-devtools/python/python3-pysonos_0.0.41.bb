DESCRIPTION = "A SoCo fork with fixes for Home Assistant "
HOMEPAGE = "https://pypi.org/project/pysonos/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=07b0e2ca9ac77cd65cd4edf2e13367ea"

SRC_URI[sha256sum] = "08d17fee04cc8209b915e61407673ea843b55792a9301fbe86adb9cc69a67a54"

inherit pypi setuptools3

RDEPENDS_${PN} += "\
	${PYTHON_PN}-ifaddr \
	${PYTHON_PN}-requests \
	${PYTHON_PN}-xmltodict \
	"
