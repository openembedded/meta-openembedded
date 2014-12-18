# Imported from git://git.yoctoproject.org/meta-cloud-services

SUMMARY = "passive checker of Python programs"
HOMEPAGE = "https://github.com/dreamhost/cliff"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://README.rst;md5=1815018ebbbfc8659a9df33681a0936e"

SRCNAME = "pyflakes"

SRC_URI = "https://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "ec94ac11cb110e6e72cca23c104b66b1"
SRC_URI[sha256sum] = "dbd2c940a1030a4f811afc1a04017a44011c0cb54f8f384b66aa624097d9b5e3"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

RDEPENDS_${PN} += " \
    python-prettytable \
    python-cmd2 \
    python-pyparsing"
