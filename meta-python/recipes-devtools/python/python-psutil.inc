SUMMARY = "A cross-platform process and system utilities module for Python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0f02e99f7f3c9a7fe8ecfc5d44c2be62"

SRC_URI[md5sum] = "01af6219b1e8fcfd53603023967713bf"
SRC_URI[sha256sum] = "8e6397ec24a2ec09751447d9f169486b68b37ac7a8d794dca003ace4efaafc6a"

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-shell \
    ${PYTHON_PN}-subprocess \
    ${PYTHON_PN}-threading \
"
