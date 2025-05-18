SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=24cb9a367a9e641b459a01c4d15256ba"

SRC_URI[sha256sum] = "db7010cd10712518819a4187baf61b05988361ea20c23e829918727b27acb977"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-numpy python3-pillow"
