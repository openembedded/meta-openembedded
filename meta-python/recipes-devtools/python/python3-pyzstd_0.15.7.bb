DESCRIPTION = "Pyzstd module provides classes and functions for compressing and \
decompressing data, using Facebook’s Zstandard (or zstd as short name) algorithm."
HOMEPAGE = "https://github.com/animalize/pyzstd"
SECTION = "devel/python"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8458383225d7107f3383ee5c521628d2"

PYPI_PACKAGE = "pyzstd"

SRC_URI[sha256sum] = "55e503f28f5a9d225ce9d0639e3f5b1801bacace5aea926ec2998e73c5150fe7"

inherit pypi setuptools3
