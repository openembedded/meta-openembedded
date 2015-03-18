SUMMARY = "Simple Python wrapper around the OpenSSL library"
SECTION = "devel/python"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRCNAME = "pyOpenSSL"

DEPENDS = "openssl"

SRC_URI = "http://pypi.python.org/packages/source/p/pyOpenSSL/pyOpenSSL-${PV}.tar.gz"

SRC_URI[md5sum] = "8579ff3a1d858858acfba5f046a4ddf7"
SRC_URI[sha256sum] = "a99db8e59c120138ad8a72eecedcc24b4510d2eed3ce48213b7e32f22cc4ee6e"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

PACKAGES =+ "${PN}-tests"
FILES_${PN}-tests = "${libdir}/${PYTHON_DIR}/site-packages/OpenSSL/test"

RDEPENDS_${PN} = "python-threading"
RDEPENDS_${PN}-tests = "${PN}"
