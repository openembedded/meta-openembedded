SUMMARY = "xxhash is a Python binding for the xxHash library by Yann Collet."
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c6a4f6d709b13927e4d5e9ff13cf8956"

DEPENDS += "python3-setuptools-scm-native"

SRC_URI[sha256sum] = "c9b288e4c1f5dae54417bc8a05e6f6daf505783d916b4783f4dd2f2e7d5db486"

SRC_URI += " \
    file://run-ptest \
"

inherit pypi python_setuptools_build_meta ptest

RDEPENDS:${PN}-ptest += " \
    python3-pytest \
    python3-unittest-automake-output \
"

do_install_ptest() {
    install -d ${D}${PTEST_PATH}/tests
    cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}
