DESCRIPTION = "Cython is a language specially designed for writing Python extension modules. \
It's designed to bridge the gap between the nice, high-level, easy-to-use world of Python \
and the messy, low-level world of C."
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=e23fadd6ceef8c618fc1c65191d846fa"
PYPI_PACKAGE = "Cython"
BBCLASSEXTEND = "native"

SRC_URI[md5sum] = "64abe5847736eb413a525b70b48afd7f"
SRC_URI[sha256sum] = "265dacf64ed8c0819f4be9355c39beaa13dc2ad2f85237a2c4e478f5ce644b48"

inherit pypi

RDEPENDS_${PN}_class-target += "\
    ${PYTHON_PN}-netserver \
    ${PYTHON_PN}-subprocess \
    ${PYTHON_PN}-shell \
"

do_install_append() {
	# Make sure we use /usr/bin/env python
	for PYTHSCRIPT in `grep -rIl '^#!.*python' ${D}`; do
		sed -i -e '1s|^#!.*|#!/usr/bin/env ${PYTHON_PN}|' $PYTHSCRIPT
	done
}
