SUMMARY = "Style preserving TOML library"
HOMEPAGE = "https://pypi.org/project/tomlkit/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=31aac0dbc1babd278d5386dadb7f8e82"

SRC_URI[sha256sum] = "08ad192699734149f5b97b45f1f18dad7eb1b6d16bc72ad0c2335772650d7b72"

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
    python3-poetry-core \
    python3-pytest \
    python3-pyyaml \
    python3-unittest-automake-output \
"

do_install_ptest() {
        install -d ${D}${PTEST_PATH}/tests
        cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

BBCLASSEXTEND = "native nativesdk"
