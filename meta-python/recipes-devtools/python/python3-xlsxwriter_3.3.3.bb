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

SRC_URI[sha256sum] = "ad3743bd5f96019ee75aae19a51e9c26faf03f218670b934dac1159692d0d5e3"

BBCLASSEXTEND = "native nativesdk"
