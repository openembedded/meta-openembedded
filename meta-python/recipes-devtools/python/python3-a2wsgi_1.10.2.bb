SUMMARY = "Convert WSGI app to ASGI app or ASGI app to WSGI app."
HOMEPAGE = "https://github.com/abersheeran/a2wsgi"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e10d05d29ec6d8be8bfc503683f1bc9a"

inherit pypi python_setuptools_build_meta ptest

SRC_URI[sha256sum] = "46b2ca427cf9ad538c145e32eb36857e1cbf477b72fe1ed0e3d35e98c06061b9"

DEPENDS += " \
        python3-pdm-native \
        python3-pdm-backend-native \
"

SRC_URI += " \
        file://run-ptest \
"

RDEPENDS:${PN}-ptest += " \
        python3-httpx \
        python3-pytest \
        python3-pytest-asyncio \
        python3-unittest-automake-output \
"

do_install_ptest() {
        install -d ${D}${PTEST_PATH}/tests
        cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

RDEPENDS:${PN} += " \
        python3-asyncio \
"
