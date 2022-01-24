SUMMARY = "Python library that provides an easy interface to read and \
write a wide range of image data, including animated images, video, \
volumetric data, and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7b9bbbb543b6af3e6b53f9b7fb68f71d"

SRC_URI[sha256sum] = "709c18f800981e4286abe4bd86b6c9b5bb6e285b6b933b5ba0962ef8e7994058"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-numpy python3-pillow"
