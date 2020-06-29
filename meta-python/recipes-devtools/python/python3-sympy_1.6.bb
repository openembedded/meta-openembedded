# This recipe is adapted from one in meta-jupyter:
# https://github.com/Xilinx/meta-jupyter/blob/master/recipes-python/python3-sympy_1.1.bb

SUMMARY = "Computer algebra system (CAS) in Python"
HOMEPAGE = "https://pypi.org/project/sympy/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ebb06e9df8f57522b72d0edb0fcf83d4"

SRC_URI[md5sum] = "dbb7b21d2972c41f37d48f744b6189a3"
SRC_URI[sha256sum] = "9769e3d2952e211b1245f1d0dfdbfbdde1f7779a3953832b7dd2b88a21ca6cc6"

inherit pypi setuptools3

RDEPENDS_${PN} += "python3-mpmath"

BBCLASSEXTEND = "native nativesdk"
