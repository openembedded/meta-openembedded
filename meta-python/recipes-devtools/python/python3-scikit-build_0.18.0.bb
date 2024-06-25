SUMMARY = "Improved build system generator for Python C/C++/Fortran/Cython extensions"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7c96d2b08b3cec6d3c67fb864d1fd8cc"

DEPENDS = "python3-hatch-vcs-native python3-hatch-fancy-pypi-readme-native"

PYPI_PACKAGE = "scikit_build"

inherit pypi python_hatchling

SRC_URI[sha256sum] = "71a13af467d1a38510c3494786e2edb73ead53ea922bde6e519dc5372aa65096"

RDEPENDS:${PN} = " \
	python3-distro \
	python3-packaging \
	python3-setuptools \
	python3-typing-extensions \
	python3-wheel \
	cmake \
"

BBCLASSEXTEND = "native nativesdk"
