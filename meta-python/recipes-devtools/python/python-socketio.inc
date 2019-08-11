SUMMARY = "Socket.IO server"
HOMEPAGE = "https://github.com/miguelgrinberg/python-socketio/"
SECTION = "devel/python"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=42d0a9e728978f0eeb759c3be91536b8"

inherit pypi

PYPI_PACKAGE = "python-socketio"

SRC_URI[md5sum] = "86cd47dddea1785ce7a6c72f55c93fe7"
SRC_URI[sha256sum] = "506b2cf7a520b40ea0b3f25e1272eff8de134dce6f471c1f6bc0de8c90fe8c57"

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-engineio \
    "
