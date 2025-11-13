SUMMARY = "Thin-wrapper around the mock package for easier use with pytest"
HOMEPAGE = "https://github.com/pytest-dev/pytest-mock/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=b2ddb1e69238461b7e4ef2a84d874109 \
"

SRC_URI += " \
    file://0001-Add-asyncio-fixture-to-test_instance_async_method_sp.patch \
    file://run-ptest \
"
SRC_URI[sha256sum] = "159e9edac4c451ce77a5cdb9fc5d1100708d2dd4ba3c3df572f14097351af80e"

inherit pypi python_setuptools_build_meta ptest

PYPI_PACKAGE = "pytest_mock"
UPSTREAM_CHECK_PYPI_PACKAGE = "${PYPI_PACKAGE}"

DEPENDS += "python3-setuptools-scm-native"

RDEPENDS:${PN}-ptest += " \
    python3-asyncio \
    python3-misc \
    python3-mock \
    python3-pytest \
    python3-pytest-asyncio \
    python3-threading \
    python3-tox \
    python3-unittest-automake-output \
"

do_install_ptest() {
    install -d ${D}${PTEST_PATH}/tests
    cp -rf ${S}/tests ${D}${PTEST_PATH}/
}
