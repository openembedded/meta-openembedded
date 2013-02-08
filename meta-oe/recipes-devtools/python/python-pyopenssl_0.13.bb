SUMMARY = "Simple Python wrapper around the OpenSSL library"
SECTION = "devel/python"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRCNAME = "pyOpenSSL"

DEPENDS = "openssl"
PR = "r1"

SRC_URI = "http://pypi.python.org/packages/source/p/pyOpenSSL/pyOpenSSL-${PV}.tar.gz"
SRC_URI[md5sum] = "767bca18a71178ca353dff9e10941929"
SRC_URI[sha256sum] = "21e12b03abaa0e04ecc8cd9c251598f71bae11c9f385304234e4ea5618c6163b"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit distutils

PACKAGES =+ "${PN}-tests"
FILES_${PN}-tests = "${libdir}/${PYTHON_DIR}/site-packages/OpenSSL/test"

RDEPENDS_${PN} = "python-threading"
RDEPENDS_${PN}-tests = "${PN}"
