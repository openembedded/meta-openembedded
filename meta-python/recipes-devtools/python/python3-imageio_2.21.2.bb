SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=24cb9a367a9e641b459a01c4d15256ba"

SRC_URI[sha256sum] = "5702e7b4874bdb2f428b08c7491699b44842129d412cdaa7177ba7ff059deb2c"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-numpy python3-pillow"
