SUMMARY = "Controller Area Network (CAN) interface module for Python"
SECTION = "devel/python"
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=38138baa100d7259934590850bc0406e"

SRC_URI[md5sum] = "d899550617614d542c0319773989920c"
SRC_URI[sha256sum] = "da89e94fa234d4ebe7f43a26d4b06a5d14ea767fbd2c4767732e4d964473dfb0"

PYPI_PACKAGE="python-can"

RDEPENDS_${PN}_class-target += "\
    ${PYTHON_PN}-argparse \
    ${PYTHON_PN}-ctypes \
    ${PYTHON_PN}-importlib \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-misc \
    ${PYTHON_PN}-netserver \
    ${PYTHON_PN}-subprocess \
    ${PYTHON_PN}-sqlite3 \
"
