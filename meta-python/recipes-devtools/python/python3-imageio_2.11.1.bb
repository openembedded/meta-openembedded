SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7b9bbbb543b6af3e6b53f9b7fb68f71d"


SRC_URI[sha256sum] = "73e53bbf21777173232cd7a1c66361516b58cd09d4828a10dcfe83bb14c2b187"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-numpy python3-pillow"
