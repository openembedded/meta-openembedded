SUMMARY = "Controller Area Network (CAN) interface module for Python"
SECTION = "devel/python"
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=38138baa100d7259934590850bc0406e"

SRC_URI[md5sum] = "670cf82f67c6d2bd6516b638f129a196"
SRC_URI[sha256sum] = "187fac2fbc807561344a6df99cf7b776c0a10182bf99bb10a5c83af45b57dc11"

PYPI_PACKAGE="python-can"

RDEPENDS_${PN}_class-target += "\
    ${PYTHON_PN}-ctypes \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-misc \
    ${PYTHON_PN}-netserver \
    ${PYTHON_PN}-sqlite3 \
"
