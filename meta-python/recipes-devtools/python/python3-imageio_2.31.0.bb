SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=24cb9a367a9e641b459a01c4d15256ba"

SRC_URI[sha256sum] = "9e2d1dec1c74ba10d58856f2f02883b84f5cd5be937a1156cc238b539a6cf068"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-numpy python3-pillow"
