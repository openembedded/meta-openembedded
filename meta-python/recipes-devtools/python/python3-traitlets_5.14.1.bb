SUMMARY = "Traitlets Python config system"
HOMEPAGE = "https://github.com/ipython/traitlets"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=13bed0ee6f46a6f6dbf1f9f9572f250a"

SRC_URI[sha256sum] = "8585105b371a04b8316a43d5ce29c098575c2e477850b62b848b964f1444527e"

inherit pypi python_hatchling ptest

SRC_URI += " \
        file://run-ptest \
"

RDEPENDS:${PN}-ptest += " \
        python3-argcomplete \
        bash \
        python3-mypy \
        python3-pytest \
        python3-pytest-mock \
        python3-unittest-automake-output \
"

do_install_ptest() {
        install -d ${D}${PTEST_PATH}/tests
        cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}
