DESCRIPTION = "Cython is a language specially designed for writing Python extension modules. \
It's designed to bridge the gap between the nice, high-level, easy-to-use world of Python \
and the messy, low-level world of C."
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=e23fadd6ceef8c618fc1c65191d846fa"
SRCNAME = "Cython"
BBCLASSEXTEND = "native"

SRC_URI = "http://www.cython.org/release/${SRCNAME}-${PV}.tar.gz"
S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI[md5sum] = "157df1f69bcec6b56fd97e0f2e057f6e"
SRC_URI[sha256sum] = "fec42fecee35d6cc02887f1eef4e4952c97402ed2800bfe41bbd9ed1a0730d8e"

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-netserver \
    ${PYTHON_PN}-subprocess \
    ${PYTHON_PN}-shell \
"
RDEPENDS_${PN}_class-native = ""
