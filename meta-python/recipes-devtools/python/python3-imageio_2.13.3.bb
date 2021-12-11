SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7b9bbbb543b6af3e6b53f9b7fb68f71d"

SRC_URI[sha256sum] = "bb87b272e1e9b05d242e11f8d88d030aefa68ca95daf3accc986d0b782ae3cd5"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-numpy python3-pillow"
