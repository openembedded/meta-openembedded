SUMMARY = "A small Python module for determining appropriate platform-specific dirs"
HOMEPAGE = "https://github.com/platformdirs/platformdirs"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ea4f5a41454746a9ed111e3d8723d17a"

SRC_URI += " \
    file://run-ptest \
"

SRC_URI[sha256sum] = "1b42b450ad933e981d56e59f1b97495428c9bd60698baab9f3eb3d00d5822421"

inherit pypi python_hatchling ptest

DEPENDS += " \
    ${PYTHON_PN}-hatch-vcs-native \
"

RDEPENDS:${PN}-ptest += " \
    ${PYTHON_PN}-appdirs \
    ${PYTHON_PN}-covdefaults \
    ${PYTHON_PN}-pytest \
    ${PYTHON_PN}-pytest-mock \
    ${PYTHON_PN}-pytest-cov \
"

do_install_ptest() {
        install -d ${D}${PTEST_PATH}
        cp -rf ${S}/tests ${D}${PTEST_PATH}/
}

BBCLASSEXTEND = "native nativesdk"
