DESCRIPTION = "Modern password hashing for your software and your servers."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8f7bb094c7232b058c7e9f2e431f389c"

DEPENDS = "python-cffi-native"

SRC_URI[md5sum] = "49d79e646dbf27a05b81e4f0c4f717c7"
SRC_URI[sha256sum] = "e54820d8b9eff357d1003f5b8d4b949a632b76b89610d8a933783fd476033ebe"

inherit pypi setuptools

RDEPENDS_${PN} = "\
    python-cffi \
    python-six \
"
