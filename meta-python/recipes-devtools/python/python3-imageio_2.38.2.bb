SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6c492278d46e42af592aa26d7cab1e54"

SRC_URI[sha256sum] = "62e338ea5c19a5547521b3b590a8a9ed1177e5d6c3fd1df076029053eacc73ca"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-wheel-native"

RDEPENDS:${PN} = "python3-numpy python3-pillow"
