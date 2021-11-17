SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7b9bbbb543b6af3e6b53f9b7fb68f71d"


SRC_URI[sha256sum] = "f3f6e9a73d62f12348f098364219f4fc64462760bc07abfe16d14db0e4974845"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-numpy python3-pillow"
