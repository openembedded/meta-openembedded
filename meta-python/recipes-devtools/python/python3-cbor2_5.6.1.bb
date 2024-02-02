DESCRIPTION = "An implementation of RFC 7049 - Concise Binary Object Representation (CBOR)."
DEPENDS +="${PYTHON_PN}-setuptools-scm-native"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI[sha256sum] = "752130e38644319f3dae7f91fabb08f58bc454ca671b72875e6228d423890efd"

inherit pypi python_setuptools_build_meta ptest

DEPENDS += "python3-setuptools-scm-native"

SRC_URI += " \
        file://run-ptest \
"

RDEPENDS:${PN}-ptest += " \
       ${PYTHON_PN}-hypothesis \
       ${PYTHON_PN}-pytest \
       ${PYTHON_PN}-unixadmin \
"

do_install_ptest() {
      install -d ${D}${PTEST_PATH}/tests
        cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-datetime \
"

BBCLASSEXTEND = "native nativesdk"
