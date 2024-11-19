SUMMARY = "Convert GeoJSON to WKT/WKB (Well-Known Text/Binary), and vice versa."
DESCRIPTION = "Convert GeoJSON to WKT/WKB (Well-Known Text/Binary), and vice versa."
HOMEPAGE = "https://github.com/geomet/geomet"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d2794c0df5b907fdace235a619d80314"
SRCNAME = "geomet"

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/geomet/geomet.git;protocol=https;branch=master"
SRCREV = "73ec5ec96cca32f2e2461d3964fc3d4ab80248f9"

inherit setuptools3
PIP_INSTALL_PACKAGE = "geomet"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-click \
    ${PYTHON_PN}-core \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-six \
"
