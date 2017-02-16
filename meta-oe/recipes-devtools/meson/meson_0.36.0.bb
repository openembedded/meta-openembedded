HOMEPAGE = "http://mesonbuild.com"
SUMMARY = "A high performance build system"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/mesonbuild/meson.git"

SRCREV = "4e3073f90a7b4f225ce7d89034d61cd978399eba"
PV = "0.36.0"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} = "ninja python3-core python3-modules"

BBCLASSEXTEND = "native"
