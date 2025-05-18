SUMMARY = "A module wrapper for os.path"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=141643e11c48898150daa83802dbc65f"

SRC_URI[sha256sum] = "2b477f5887033f3cbea1cfd8553ee6a6a498eb2540a19f4aa082822aadcea30a"

SRC_URI += "\
    file://run-ptest \
"

inherit pypi python_setuptools_build_meta ptest

DEPENDS += "python3-setuptools-scm-native"

RDEPENDS:${PN} += " \
    python3-appdirs \
    python3-crypt \
    python3-io \
    python3-numbers \
    python3-shell \
"
RDEPENDS:${PN}-ptest += " \
    python3-pytest \
    python3-unittest-automake-output \
"

BBCLASSEXTEND = "nativesdk native"

do_install_ptest() {
    install -d ${D}${PTEST_PATH}/test
    cp -rf ${S}/test_* ${D}${PTEST_PATH}/test/
    install -d ${D}${PTEST_PATH}/path
    cp -rf ${S}/path/* ${D}${PTEST_PATH}/path/
}
