SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7b9bbbb543b6af3e6b53f9b7fb68f71d"


SRC_URI[sha256sum] = "469c59fe71c81cdc41c84f842d62dd2739a08fac8cb85f5a518a92a6227e2ed6"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-numpy python3-pillow"
