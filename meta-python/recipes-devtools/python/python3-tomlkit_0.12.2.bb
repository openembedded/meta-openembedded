SUMMARY = "Style preserving TOML library"
HOMEPAGE = "https://pypi.org/project/tomlkit/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=31aac0dbc1babd278d5386dadb7f8e82"

SRC_URI[sha256sum] = "df32fab589a81f0d7dc525a4267b6d7a64ee99619cbd1eeb0fae32c1dd426977"

inherit pypi python_poetry_core ptest

SRC_URI += " \
        file://run-ptest \
"

RDEPENDS:${PN} += " \
    python3-datetime \
    python3-profile \
    python3-stringold \
"

RDEPENDS:${PN}-ptest += " \
        ${PYTHON_PN}-poetry-core \
        ${PYTHON_PN}-pytest \
        ${PYTHON_PN}-pyyaml \
"

do_install_ptest() {
        install -d ${D}${PTEST_PATH}/tests
        cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

BBCLASSEXTEND = "native nativesdk"
