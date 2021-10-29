SUMMARY = "SMPP library for python"
SECTION = "devel/python"
LICENSE = "GPLv3.0"
LIC_FILES_CHKSUM = "file://README.md;md5=8b4e2ac8cf248f7b991784f88b630852"

PYPI_PACKAGE = "smpplib"
SRC_URI[sha256sum] = "3d513178a35573f66faac4ef2127c4bd73307ddb463d145b17b013cf709d9ddd"

inherit pypi setuptools3 ptest

SRC_URI += " \
        file://run-ptest \
"

RDEPENDS:${PN} += " \
        ${PYTHON_PN}-logging \
        ${PYTHON_PN}-six \
"

RDEPENDS:${PN}-ptest += " \
        ${PYTHON_PN}-pytest \
        ${PYTHON_PN}-unittest \
        ${PYTHON_PN}-profile \
        ${PYTHON_PN}-mock \
"

do_install_ptest() {
        install -d ${D}${PTEST_PATH}/tests
        cp -rf ${S}/smpplib/tests/* ${D}${PTEST_PATH}/tests/
}
