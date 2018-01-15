SUMMARY = "Cryptographic library for Python"
DESCRIPTION = "PyCryptodome is a self-contained Python package of low-level\
 cryptographic primitives."
HOMEPAGE = "http://www.pycryptodome.org"
LICENSE = "PD & BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=690cd8c71f0367a558f24fe53d943a2e"

SRC_URI[md5sum] = "8744863c7d5980ae3f81c2e288701a61"
SRC_URI[sha256sum] = "18d8dfe31bf0cb53d58694903e526be68f3cf48e6e3c6dfbbc1e7042b1693af7"

inherit pypi

BBCLASSEXTEND = "native nativesdk"
