SUMMARY = "Python project template generator with batteries included"
DESCRIPTION = "PyScaffold package helps to setup a new Python project. \
After installation, it provides a new command [putup], which could be \
used to create template Projects."

HOMEPAGE = "https://github.com/pyscaffold/pyscaffold"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=8227180126797a0148f94f483f3e1489"

inherit pypi setuptools3

PYPI_PACKAGE = "PyScaffold"

SRC_URI[sha256sum] = "ff83ea4e585b29a746857c49a4dcba80795af130f62ff3f722d9f39aa4ff2a75"

BBCLASSEXTEND = "native nativesdk"

RDEPENDS:${PN} += " \
	python3-email \
	python3-compression \
"
