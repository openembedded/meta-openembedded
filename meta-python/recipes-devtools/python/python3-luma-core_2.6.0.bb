SUMMARY = "A component library to support SBC display drivers"
DESCRIPTION = "A component library to support SBC display drivers"
HOMEPAGE = "https://github.com/rm-hull/luma.core"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=a22ee4610fe68b60c67e2ea3cc8e2613"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-wheel-native"

SRC_URI[sha256sum] = "3ddb673f9699a148d3ccfee2985451b01ddd4b4ea66b65a9664ce36806cf411c"

PYPI_PACKAGE = "luma.core"
PYPI_PACKAGE_SDIST = "luma_core"

RDEPENDS:${PN} += " \
	python3-pillow \
	python3-threading \
	python3-smbus2 \
"
