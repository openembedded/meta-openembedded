SUMMARY = "C++ headers for C extension development"
HOMEPAGE = "https://cppy.readthedocs.io/en/latest/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0bfb3e39b13587f0028f17baf0e42371"

SRC_URI[sha256sum] = "83b43bf17b1085ac15c5debdb42154f138b928234b21447358981f69d0d6fe1b"

RDEPENDS:${PN} += "python3-setuptools python3-distutils"

inherit pypi python_flit_core 

SRC_URI += " file://0001-Fix-build-error-as-following.patch \
           "

DEPENDS += "python3-setuptools-native"

BBCLASSEXTEND = "native nativesdk"
