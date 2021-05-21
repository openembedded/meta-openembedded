DESCRIPTION = "A SoCo fork with fixes for Home Assistant "
HOMEPAGE = "https://pypi.org/project/pysonos/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=07b0e2ca9ac77cd65cd4edf2e13367ea"

SRC_URI[sha256sum] = "77b8987ddbc69ea5c28ec1dee09cd6c30267e196521b1847e43cdea77bce4f09"

inherit pypi setuptools3

RDEPENDS_${PN} += "\
	${PYTHON_PN}-ifaddr \
	${PYTHON_PN}-requests \
	${PYTHON_PN}-xmltodict \
	"
