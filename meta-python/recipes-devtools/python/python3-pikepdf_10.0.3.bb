SUMMARY = "Read and write PDFs with Python, powered by qpdf"
HOMEPAGE = "https://github.com/pikepdf/pikepdf"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9741c346eef56131163e13b9db1241b3"

SRC_URI[sha256sum] = "ff12a044d79e342eff6bc8b27dfd4c24b395943052169bb43eea4a23a4100e56"

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
