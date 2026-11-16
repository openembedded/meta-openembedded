SUMMARY = "Python's missing debug print command, and more."
DESCRIPTION = "Python's missing debug print command, and more."
HOMEPAGE = "https://github.com/samuelcolvin/python-devtools"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3c69d58fa5f4ff0b4244368d39546751"

SRC_URI[sha256sum] = "e3a848080404bd4e10dbf535fe016c6b593fccf24a8edac863048b8cbab3d92f"

inherit pypi python_uv_build ptest-python-pytest

RDEPENDS:${PN}-ptest += "\
    python3-core \
    python3-executing \
    python3-numpy \
    python3-asttokens \
"

BBCLASSEXTEND = "native nativesdk"
