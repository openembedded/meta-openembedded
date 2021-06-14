DESCRIPTION = "A SoCo fork with fixes for Home Assistant "
HOMEPAGE = "https://pypi.org/project/pysonos/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=07b0e2ca9ac77cd65cd4edf2e13367ea"

SRC_URI[sha256sum] = "828ad17a671dca2c52976efd9404254ad667f75482e33ce2de2e68e89d0e56ef"

inherit pypi setuptools3

RDEPENDS_${PN} += "\
	${PYTHON_PN}-ifaddr \
	${PYTHON_PN}-requests \
	${PYTHON_PN}-xmltodict \
	"
