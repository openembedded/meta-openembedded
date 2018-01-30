SUMMARY = "Cryptographic library for Python"
DESCRIPTION = "PyCryptodome is a self-contained Python package of low-level\
 cryptographic primitives."
HOMEPAGE = "http://www.pycryptodome.org"
LICENSE = "PD & BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=775fe77831c03bf82f35abc0e230d4c1"

SRC_URI[md5sum] = "030c40da6377bbc82deef8e884fa22cd"
SRC_URI[sha256sum] = "ede7f1f44b0895feb8ddbf882d1a7eb504deb3fa34fcc4860d6065c25cbdf175"

inherit pypi

BBCLASSEXTEND = "native nativesdk"
