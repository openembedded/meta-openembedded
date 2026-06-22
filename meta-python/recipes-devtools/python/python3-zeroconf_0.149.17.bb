SUMMARY = "Pure Python Multicast DNS Service Discovery Library (Bonjour/Avahi compatible)"
HOMEPAGE = "https://github.com/jstasiak/python-zeroconf"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=9fe712b1bc27c5c4e9ecd7f31d208900"

SRC_URI[sha256sum] = "f10e58071bc5e749176b8dc8d7e7ed4d2e6b507dde20fd8169b760cf4f7f4c5a"

SRC_URI += "file://run-ptest"

inherit pypi python_poetry_core cython ptest

RDEPENDS:${PN} += " \
    python3-ifaddr (>=0.1.7) \
    python3-async-timeout \
"

RDEPENDS:${PN}-ptest += " \
    python3-pytest \
    python3-pytest-asyncio \
    python3-pytest-codspeed \
    python3-unittest-automake-output \
"

do_install_ptest() {
    install -d ${D}${PTEST_PATH}/tests
    cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}
