DESCRIPTION = "Python Bindings for libphone-utils"
SECTION = "devel/python"
DEPENDS = "libphone-utils python-cython-native python-pyrex-native"
RDEPENDS_${PN} = "libphone-utils"
LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://phoneutils/c_phoneutils.pyx;endline=18;md5=ca321e4ec3a30a44469b23ebca782665"

SRCREV = "8a7c719e0c3f1f8c10f77f17422da02d7177f0dd"
PV = "0.0.2+gitr${SRCPV}"
PR = "r4"

SRC_URI = "git://git.shr-project.org/repo/libphone-utils.git;protocol=http;branch=master"
S = "${WORKDIR}/git/src/python"

inherit setuptools
