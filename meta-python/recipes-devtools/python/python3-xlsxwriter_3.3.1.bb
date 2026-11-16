SUMMARY = "Python 2 and 3 compatibility library"
HOMEPAGE = "https://xlsxwriter.readthedocs.io"
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=5249ec013d324b4f1e23d5b019e679ca"

inherit pypi python_setuptools_build_meta


RDEPENDS:${PN} += " \
	python3-crypt \
	python3-datetime \
	python3-compression \
	python3-numbers \
	python3-io \
"

SRC_URI[sha256sum] = "0867761912cceac76f97ffa76eeda18c623ae9217f601be8512f55b1af4bdb6e"

BBCLASSEXTEND = "native nativesdk"
