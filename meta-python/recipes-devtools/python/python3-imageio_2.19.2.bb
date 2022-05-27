SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=24cb9a367a9e641b459a01c4d15256ba"

SRC_URI[sha256sum] = "46e1e74128837d2a1ebc87476b7f73978b69a128fa238bc989b625a9819bd9b3"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-numpy python3-pillow"
