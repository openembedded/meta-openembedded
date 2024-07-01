SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=24cb9a367a9e641b459a01c4d15256ba"

SRC_URI[sha256sum] = "5c0c0ee8faa018a1c42f649b90395dd4d3bb6187c09053a0cd6f1fdd51bbff5e"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-numpy python3-pillow"
