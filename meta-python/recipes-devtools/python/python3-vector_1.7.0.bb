SUMMARY = "Python library for arrays of 2D, 3D, and Lorentz vectors"
DESCRIPTION = "\
    Vector is a Python library for 2D and 3D spatial vectors, as well as 4D space-time vectors. \
    It is especially intended for performing geometric calculations on arrays of vectors, rather \
    than one vector at a time in a Python for loop.\
"
HOMEPAGE = "https://github.com/scikit-hep/vector"
BUGTRACKER = "https://github.com/scikit-hep/vector/issues"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2963f0dc7a24919505850460dd1a785b"

DEPENDS += "python3-hatch-vcs-native"

SRC_URI[sha256sum] = "8b05ea2953322c74c49d2af180f7eac73ad0aa3d4c391cab896175ec4fa3d642"

inherit pypi python_hatchling

RDEPENDS:${PN} += "\
    python3-numpy \
    python3-packaging \
"
