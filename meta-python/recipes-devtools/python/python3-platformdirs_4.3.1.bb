SUMMARY = "A small Python module for determining appropriate platform-specific dirs"
HOMEPAGE = "https://github.com/platformdirs/platformdirs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ea4f5a41454746a9ed111e3d8723d17a"

SRC_URI += " \
    file://run-ptest \
"

SRC_URI[sha256sum] = "63b79589009fa8159973601dd4563143396b35c5f93a58b36f9049ff046949b1"

inherit pypi python_hatchling ptest

DEPENDS += " \
    python3-hatch-vcs-native \
"

RDEPENDS:${PN}-ptest += " \
    python3-appdirs \
    python3-covdefaults \
    python3-pytest \
    python3-pytest-cov \
    python3-pytest-mock \
    python3-unittest-automake-output \
"

do_install_ptest() {
        install -d ${D}${PTEST_PATH}
        cp -rf ${S}/tests ${D}${PTEST_PATH}/
}

BBCLASSEXTEND = "native nativesdk"
