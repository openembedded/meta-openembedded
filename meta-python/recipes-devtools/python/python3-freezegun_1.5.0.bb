SUMMARY = "FreezeGun is a library that allows your Python tests to travel through time by mocking the datetime module."
HOMEPAGE = "https://github.com/spulec/freezegun"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=acf1d209bb6eddae4cbe6ffd6a0144fe"

SRC_URI[sha256sum] = "200a64359b363aa3653d8aac289584078386c7c3da77339d257e46a01fb5c77c"

inherit pypi python_setuptools_build_meta ptest

SRC_URI += " \
        file://run-ptest \
"

RDEPENDS:${PN}-ptest += " \
        python3-pytest \
        python3-sqlite3 \
        python3-unittest-automake-output \
"

do_install_ptest() {
        install -d ${D}${PTEST_PATH}/tests
        cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

RDEPENDS:${PN} = "\
        python3-asyncio \
        python3-dateutil \
        python3-unittest \
"
