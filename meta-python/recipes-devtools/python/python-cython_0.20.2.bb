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

SRC_URI[md5sum] = "7fc13e1c665bdf7cea19ad08906af91f"
SRC_URI[sha256sum] = "ed13b606a2aeb5bd6c235f8ed6c9988c99d01a033d0d21d56137c13d5c7be63f"

inherit setuptools

RDEPENDS_${PN} += "\
    python-distribute \
    python-netserver \
    python-subprocess \
    python-shell \
"
RDEPENDS_${PN}_class-native = ""
