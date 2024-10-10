SUMMARY = "Fast property caching"
HOMEPAGE = "https://github.com/aio-libs/propcache"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[sha256sum] = "df81779732feb9d01e5d513fad0122efb3d53bbc75f61b2a4f29a020bc985e70"

inherit pypi python_setuptools_build_meta

DEPENDS += " \
    python3-expandvars-native \
    python3-cython-native \
"
