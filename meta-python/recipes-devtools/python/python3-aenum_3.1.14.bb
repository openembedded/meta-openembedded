SUMMARY = "Advanced Enumerations library"
HOMEPAGE = "https://pypi.org/project/aenum/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://aenum/LICENSE;md5=c6a85477543f8b8591b9c1f82abebbe9"

SRC_URI[sha256sum] = "7c4b04b5c9621533d6311e6ca23ea2ee213c7a992ed0be79a2b944cdaf2a45ec"

inherit pypi setuptools3

RDEPENDS:${PN} += "\
	${PYTHON_PN}-pprint \
"

BBCLASSEXTEND = "native nativesdk"
