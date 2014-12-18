# Imported from git://git.yoctoproject.org/meta-cloud-services

SUMMARY = "Python style guide checker"
HOMEPAGE = "https://github.com/dreamhost/cliff"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://README.rst;md5=b0d37793ab91ca25ad5c200e9ea22331"

SRCNAME = "pep8"

SRC_URI = "https://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "a03bb494859e87b42601b61b1b043a0c"
SRC_URI[sha256sum] = "603a46e5c358ce20ac4807a0eeafac7505d1125a4c1bd8378757ada06f61bed8"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

RDEPENDS_${PN} += " \
    python-prettytable \
    python-cmd2 \
    python-pyparsing"
