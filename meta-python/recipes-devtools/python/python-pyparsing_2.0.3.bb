# Imported from git://git.yoctoproject.org/meta-cloud-services

SUMMARY = "Python parsing module"
HOMEPAGE = "http://pyparsing.wikispaces.com/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fb46329938e6bc829b256e37d5c1e31a"

SRCNAME = "pyparsing"

SRC_URI = "http://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.zip"

SRC_URI[md5sum] = "0a5ec41bb650aed802751a311b5d820d"
SRC_URI[sha256sum] = "43d7710fdaa194a3bccf3127d06dec8e2de99a7efe6c47f01d4b748a1d04b192"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit distutils
