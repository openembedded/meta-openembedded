DESCRIPTION = "Bindings for the scrypt key derivation function library"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=05b5ac2824a7ae7489193b0f6a6f2cd1"
HOMEPAGE="https://github.com/holgern/py-scrypt"

SRC_URI += "file://0001-py-scrypt-remove-the-hard-coded-include-paths.patch"

SRC_URI[sha256sum] = "98ffde45e4a95461d73ded54ba7a26857679920d4f8ff320f6f7ade6e29531bd"

inherit pypi ptest setuptools3 dos2unix

SRC_URI += " \
    file://run-ptest \
"

RDEPENDS:${PN}-ptest += " \
    python3-pytest \
    python3-unittest-automake-output \
"

RDEPENDS:${PN} += " \
    python3-ctypes \
"

do_install_ptest() {
    install -d ${D}${PTEST_PATH}/tests
    cp -rf ${S}/scrypt/tests/* ${D}${PTEST_PATH}/tests/
}
