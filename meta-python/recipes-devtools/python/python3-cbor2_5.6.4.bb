DESCRIPTION = "An implementation of RFC 7049 - Concise Binary Object Representation (CBOR)."
DEPENDS +="python3-setuptools-scm-native"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a79e64179819c7ce293372c059f1dbd8"

SRC_URI[sha256sum] = "1c533c50dde86bef1c6950602054a0ffa3c376e8b0e20c7b8f5b108793f6983e"

inherit pypi python_setuptools_build_meta ptest

DEPENDS += "python3-setuptools-scm-native"

SRC_URI += " \
        file://run-ptest \
"

RDEPENDS:${PN}-ptest += " \
    python3-hypothesis \
    python3-pytest \
    python3-unittest-automake-output \
    python3-unixadmin \
"

do_install_ptest() {
	install -d ${D}${PTEST_PATH}/tests
        cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

RDEPENDS:${PN} += " \
    python3-datetime \
"

BBCLASSEXTEND = "native nativesdk"
