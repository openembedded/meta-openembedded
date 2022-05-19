SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=24cb9a367a9e641b459a01c4d15256ba"

SRC_URI[sha256sum] = "d63600626b80ebe1ec9614c33434879c754dea328717f4e2cebe88f156fa1a9c"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-numpy python3-pillow"
