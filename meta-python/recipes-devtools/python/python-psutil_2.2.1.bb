SUMMARY = "A cross-platform process and system utilities module for Python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0f02e99f7f3c9a7fe8ecfc5d44c2be62"

SRC_URI[md5sum] = "1a2b58cd9e3a53528bb6148f0c4d5244"
SRC_URI[sha256sum] = "a0e9b96f1946975064724e242ac159f3260db24ffa591c3da0a355361a3a337f"

RDEPENDS_${PN} += " \
    python-subprocess \
    "

inherit pypi
