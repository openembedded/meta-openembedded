SUMMARY = "Advanced Enumerations library"
HOMEPAGE = "https://pypi.org/project/aenum/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://aenum/LICENSE;md5=c6a85477543f8b8591b9c1f82abebbe9"

SRC_URI[sha256sum] = "1d494e4d3b3e3e95389aee420203d945776d2430ee9a3d4f162e267b3a7ec3a6"

inherit pypi setuptools3

BBCLASSEXTEND = "native nativesdk"
