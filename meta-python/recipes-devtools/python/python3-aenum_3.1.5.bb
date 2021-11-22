SUMMARY = "Advanced Enumerations library"
HOMEPAGE = "https://pypi.org/project/aenum/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://aenum/LICENSE;md5=c6a85477543f8b8591b9c1f82abebbe9"

SRC_URI[sha256sum] = "2ebad8590b6a0183c0d9893523b458edce987ae4533339c5ac185cfac32daf1a"

inherit pypi setuptools3

RDEPENDS:${PN} += "\
	${PYTHON_PN}-pprint \
"

BBCLASSEXTEND = "native nativesdk"
