SUMMARY = "Computer algebra system (CAS) in Python"
HOMEPAGE = "https://pypi.org/project/sympy/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ea48085d7dff75b49271b25447e8cdca"

SRC_URI[sha256sum] = "9cebf7e04ff162015ce31c9c6c9144daa34a93bd082f54fd8f12deca4f47515f"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    python3-misc \
    python3-mpmath \
"

BBCLASSEXTEND = "native nativesdk"
