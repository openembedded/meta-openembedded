DESCRIPTION = "Cython is a language specially designed for writing Python extension modules. \
It's designed to bridge the gap between the nice, high-level, easy-to-use world of Python \
and the messy, low-level world of C."
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=e23fadd6ceef8c618fc1c65191d846fa"
PYPI_PACKAGE = "Cython"
BBCLASSEXTEND = "native"

SRC_URI[md5sum] = "3a26a5940da1e9f95d8776eabd888b07"
SRC_URI[sha256sum] = "b932b5194e87a8b853d493dc1b46e38632d6846a86f55b8346eb9c6ec3bdc00b"

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
