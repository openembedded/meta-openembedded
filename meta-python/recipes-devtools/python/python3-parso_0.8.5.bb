SUMMARY = "A Python Parser"
HOMEPAGE = "https://github.com/davidhalter/parso"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=cbaa2675b2424d771451332a7a69503f"

PYPI_PACKAGE = "parso"

SRC_URI[sha256sum] = "034d7354a9a018bdce352f48b2a8a450f05e9d6ee85db84764e9b6bd96dafe5a"

inherit setuptools3 pypi

RDEPENDS:${PN} = " \
	python3-crypt \
	python3-difflib \
	python3-logging \
"
