SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=24cb9a367a9e641b459a01c4d15256ba"

SRC_URI[sha256sum] = "5db5087be5c814ecf7e2c7d30a1a15c97eca97d8c26f31ddc54d767d4a43bce8"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-numpy python3-pillow"
