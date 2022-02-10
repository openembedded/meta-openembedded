SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7b9bbbb543b6af3e6b53f9b7fb68f71d"

SRC_URI[sha256sum] = "d0d7abb4e5c4044c06fc573233489c4a25582698f93ca94f7bd70b6f4ab172ec"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-numpy python3-pillow"
