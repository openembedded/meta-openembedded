SUMMARY = "Simple Python wrapper around the OpenSSL library"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS = "openssl python-cryptography"

SRC_URI[md5sum] = "9587d813dcf656e9f2760e41a3682dc3"
SRC_URI[sha256sum] = "363d10ee43d062285facf4e465f4f5163f9f702f9134f0a5896f134cbb92d17d"

PYPI_PACKAGE = "pyOpenSSL"
inherit pypi setuptools

PACKAGES =+ "${PN}-tests"
FILES_${PN}-tests = "${libdir}/${PYTHON_DIR}/site-packages/OpenSSL/test"

RDEPENDS_${PN} = "python-threading python-six python-cryptography"
RDEPENDS_${PN}-tests = "${PN}"
