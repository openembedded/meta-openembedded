DESCRIPTION = "Cython is a language specially designed for writing Python extension modules. \
It's designed to bridge the gap between the nice, high-level, easy-to-use world of Python \
and the messy, low-level world of C."
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=e23fadd6ceef8c618fc1c65191d846fa"
SRCNAME = "Cython"
BBCLASSEXTEND = "native"
NATIVE_INSTALL_WORKS = "1"
DEPENDS = "python"

SRC_URI = "http://www.cython.org/release/${SRCNAME}-${PV}.tar.gz"
S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI[md5sum] = "ed2ec5e0df60a6b8e500bedf93bb1feb"
SRC_URI[sha256sum] = "a30d5e26c82ec65d65703c7cf312161a21b30339addc1a42d3b2965dea254ca6"

inherit distutils
