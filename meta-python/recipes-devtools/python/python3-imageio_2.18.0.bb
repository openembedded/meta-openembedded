SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=24cb9a367a9e641b459a01c4d15256ba"

SRC_URI[sha256sum] = "090898c48532631ab11c74ae743e64c24dabda45c16db46f7e3bec9e2d8f422f"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-numpy python3-pillow"
