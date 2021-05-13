SUMMARY = "Various helpers to pass trusted data to untrusted environments and back."
HOMEPAGE = "http://github.com/mitsuhiko/itsdangerous"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=4cda9a0ebd516714f360b0e9418cfb37"

SRC_URI[sha256sum] = "99b1053ccce68066dfc0b4465ef8779027e6d577377c8270e21a3d6289cac111"

inherit pypi setuptools3

CLEANBROKEN = "1"

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-simplejson \
    ${PYTHON_PN}-netclient \
    ${PYTHON_PN}-compression \
"
