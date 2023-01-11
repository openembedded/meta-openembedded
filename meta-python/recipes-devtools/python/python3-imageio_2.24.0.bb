SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=24cb9a367a9e641b459a01c4d15256ba"

SRC_URI[sha256sum] = "f240f8229f4f329a1546281194b52da5d6694141a524668fed3f81b0d07782fa"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-numpy python3-pillow"
