SUMMARY = "Read and write PDFs with Python, powered by qpdf"
HOMEPAGE = "https://github.com/pikepdf/pikepdf"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9741c346eef56131163e13b9db1241b3"

SRC_URI[sha256sum] = "d75778283c354580a462d31bd4334f6ba92225e41ccd8bb949ec6e98a23d4eb2"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE = "pikepdf"

CVE_PRODUCT = "pikepdf"

DEPENDS += " \
	python3-pybind11-native \
	qpdf \
"

RDEPENDS:${PN} += " \
	python3-pillow \
	python3-lxml \
"

BBCLASSEXTEND = "native nativesdk"
