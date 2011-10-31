DESCRIPTION = "Cython is a language specially designed for writing Python extension modules. \
It's designed to bridge the gap between the nice, high-level, easy-to-use world of Python \
and the messy, low-level world of C."
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=e23fadd6ceef8c618fc1c65191d846fa"
SRCNAME = "Cython"
BBCLASSEXTEND = "native"
PR = "r2"

SRC_URI = "http://www.cython.org/release/${SRCNAME}-${PV}.tar.gz \
           file://0001-Don-t-append-docs-to-_Extension.extension.__doc__.patch \
"
S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI[md5sum] = "171021b3845c9ca8bd6d8185b3cde664"
SRC_URI[sha256sum] = "248123f2f02f2701a28d9452132ac835533b396a273cda9d361cb65c26067edc"

inherit distutils
