SUMMARY = "The module provides handy URL class for url parsing and changing"
HOMEPAGE = "https://github.com/aio-libs/yarl/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[sha256sum] = "686d51e51ee5dfe62dec86e4866ee0e9ed66df700d55c828a615640adc885307"

PYPI_PACKAGE = "yarl"

inherit pypi ptest-python-pytest python_setuptools_build_meta cython

DEPENDS += " \
    python3-expandvars-native \
"

RDEPENDS:${PN} = "\
    python3-multidict \
    python3-idna \
    python3-io \
    python3-propcache \
"

RDEPENDS:${PN}-ptest += " \
    python3-hypothesis \
    python3-image \
    python3-pytest \
    python3-pytest-codspeed \
    python3-rich \
    python3-unittest-automake-output \
"

