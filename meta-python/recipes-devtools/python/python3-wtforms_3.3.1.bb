DESCRIPTION = "A flexible forms validation and rendering library for python web development."
HOMEPAGE = "https://pypi.python.org/pypi/WTForms"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=17ed54aa95f4a6cd0d7a4747d14b64d0"

SRC_URI[sha256sum] = "9d10ccb5740290c9ae6673522d87bcae3b669cf0cb4e4584aee229b4694af10c"

inherit pypi python_hatchling

DEPENDS += "\
    python3-pip-native \
    python3-babel-native \
    "

RDEPENDS:${PN} += "\
    python3-netserver \
    python3-numbers \
    python3-markupsafe \
    "
