SUMMARY = "Advanced Enumerations library"
HOMEPAGE = "https://pypi.org/project/aenum/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://aenum/LICENSE;md5=c6a85477543f8b8591b9c1f82abebbe9"

SRC_URI[sha256sum] = "806dd4791298e19daff2cdfe7be3ae6d931d0d03097973f802b3ea55066f62dd"

inherit pypi setuptools3

BBCLASSEXTEND = "native nativesdk"
