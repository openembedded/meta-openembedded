SUMMARY = "A cross-platform process and system utilities module for Python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0f02e99f7f3c9a7fe8ecfc5d44c2be62"

SRC_URI[md5sum] = "3090e32d42761086ae82036cadb95ba3"
SRC_URI[sha256sum] = "959bd58bdc8152b0a143cb3bd822d4a1b8f7230617b0e3eb2ff6e63812120f2b"

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-shell \
    ${PYTHON_PN}-subprocess \
    ${PYTHON_PN}-threading \
"
