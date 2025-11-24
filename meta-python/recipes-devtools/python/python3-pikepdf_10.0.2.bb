SUMMARY = "Read and write PDFs with Python, powered by qpdf"
HOMEPAGE = "https://github.com/pikepdf/pikepdf"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9741c346eef56131163e13b9db1241b3"

SRC_URI[sha256sum] = "7c85a2526253e35575edb2e28cdc740d004be4b7c5fda954f0e721ee1c423a52"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE = "pikepdf"

DEPENDS += " \
	python3-pybind11-native \
	qpdf \
"

RDEPENDS:${PN} += " \
	python3-pillow \
	python3-lxml \
"

BBCLASSEXTEND = "native nativesdk"
