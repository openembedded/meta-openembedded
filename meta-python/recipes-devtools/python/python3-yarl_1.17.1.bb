SUMMARY = "The module provides handy URL class for url parsing and changing"
HOMEPAGE = "https://github.com/aio-libs/yarl/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[sha256sum] = "067a63fcfda82da6b198fa73079b1ca40b7c9b7994995b6ee38acda728b64d47"

SRC_URI += "file://run-ptest"

PYPI_PACKAGE = "yarl"

inherit pypi ptest python_setuptools_build_meta

DEPENDS += " \
    python3-expandvars-native \
    python3-cython-native \
"

RDEPENDS:${PN} = "\
    python3-multidict \
    python3-idna \
    python3-io \
    python3-propcache \
"

RDEPENDS:${PN}-ptest += " \
    python3-hypothesis \
    python3-pytest \
    python3-pytest-codspeed \
    python3-unittest-automake-output \
"

do_install_ptest() {
    install -d ${D}${PTEST_PATH}/tests
    cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}
