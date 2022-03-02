SUMMARY = "Python project template generator with batteries included"
DESCRIPTION = "PyScaffold package helps to setup a new Python project. \
After installation, it provides a new command [putup], which could be \
used to create template Projects."

HOMEPAGE = "https://github.com/pyscaffold/pyscaffold"
SECTION = "devel/python"
LICENSE = "0BSD & MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=14a49c74a1d91829908ac756c07e6b91"
DEPENDS += "python3-setuptools-scm-native"

SRC_URI[sha256sum] = "46a2bbdf255ba2efc6c56ae1428249b61d56c4a3e54ef3db0d05fa97792011a5"

inherit pypi setuptools3
PYPI_PACKAGE = "PyScaffold"

RDEPENDS:${PN} += " \
	python3-email \
	python3-compression \
"

BBCLASSEXTEND = "native nativesdk"
