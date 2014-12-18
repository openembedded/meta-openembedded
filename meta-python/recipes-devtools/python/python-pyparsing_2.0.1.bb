# Imported from git://git.yoctoproject.org/meta-cloud-services

SUMMARY = "Python parsing module"
HOMEPAGE = "http://pyparsing.wikispaces.com/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fb46329938e6bc829b256e37d5c1e31a"

SRCNAME = "pyparsing"

SRC_URI = "http://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.zip"

SRC_URI[md5sum] = "dc7036a6f1ab01c3baed3d97af8d77c0"
SRC_URI[sha256sum] = "7e1766ee747cca79fe172b670fd53ef58fc55d2b1804fd6c66d857abb62d143a"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit distutils
