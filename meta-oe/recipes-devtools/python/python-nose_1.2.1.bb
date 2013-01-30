DESCRIPTION = "nose extends the test loading and running features of unittest, \
making it easier to write, find and run tests."
SECTION = "devel/python"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://lgpl.txt;md5=a6f89e2100d9b6cdffcea4f398e37343"

SRC_URI = "http://pypi.python.org/packages/source/n/nose/nose-${PV}.tar.gz"

SRC_URI[md5sum] = "735e3f1ce8b07e70ee1b742a8a53585a"
SRC_URI[sha256sum] = "2171e9202d118d302d5db1decb52dd862b79e2a626ca19653a6914574a6ca7d9"

S = "${WORKDIR}/nose-${PV}"

inherit distutils
