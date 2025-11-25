SUMMARY = "Typing stubs for psutil"
HOMEPAGE = "https://github.com/python/typeshed"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ec038232ab86edd7354b091c54e190e2"

PYPI_PACKAGE = "types_psutil"
UPSTREAM_CHECK_PYPI_PACKAGE = "${PYPI_PACKAGE}"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "cdb39c30a81ce6e433aa672922d59b78c4c6a9c64cd9936f1f6894d26c82ae0f"

BBCLASSEXTEND = "native"

RDEPENDS:${PN} += "\
	python3-psutil \
"
