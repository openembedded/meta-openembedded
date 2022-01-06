SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7b9bbbb543b6af3e6b53f9b7fb68f71d"

SRC_URI[sha256sum] = "c7ec2be58e401b6eaa838f8eaf8368ed54b2de4a1b001fe6551644f1a30a843d"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-numpy python3-pillow"
