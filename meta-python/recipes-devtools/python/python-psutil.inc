SUMMARY = "A cross-platform process and system utilities module for Python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0f02e99f7f3c9a7fe8ecfc5d44c2be62"

SRC_URI[md5sum] = "c0aa7823e416b4e2ee908c2d387d2662"
SRC_URI[sha256sum] = "42e2de159e3c987435cb3b47d6f37035db190a1499f3af714ba7af5c379b6ba2"

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-shell \
    ${PYTHON_PN}-subprocess \
    ${PYTHON_PN}-threading \
"
