SUMMARY = "A simple optionally-async python inotify library, focused on simplicity of use and operation, and leveraging modern Python features"
HOMEPAGE = "https://gitlab.com/Taywee/asyncinotify"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f75d2927d3c1ed2414ef72048f5ad640"

SRC_URI[sha256sum] = "3321deedc443c8402229a423623d3ae2fc17c433b9b9bfe170828ee0c7ea3871"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += " \
    python3-asyncio \
    python3-core  \
    python3-ctypes \
    python3-io \
"
