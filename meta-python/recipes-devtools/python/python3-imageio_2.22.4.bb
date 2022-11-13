SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=24cb9a367a9e641b459a01c4d15256ba"

SRC_URI[sha256sum] = "0fae027addf02bc89c73a56cc157ad84557f8b8b84aa19b4cb706fefca2d88ff"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-numpy python3-pillow"
