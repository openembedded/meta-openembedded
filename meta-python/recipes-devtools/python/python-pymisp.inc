DESCRIPTION = "Python API for MISP"
HOMEPAGE = "https://github.com/MISP/PyMISP"
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a3639cf5780f71b125d3e9d1dc127c20"
SRCREV = "a7fc1ceb20d455ccfa2ba7af229048330e22b173"
PR = "r0"

SRC_URI = "git://github.com/MISP/PyMISP.git;branch=master"

SRCNAME = "pymisp"
S = "${WORKDIR}/git"

RDEPENDS_${PN} += " ${PYTHON_PN}-requests"
