DESCRIPTION = "An implementation of RFC 7049 - Concise Binary Object Representation (CBOR)."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a79e64179819c7ce293372c059f1dbd8"
DEPENDS += "python3-setuptools-scm-native"

SRC_URI[sha256sum] = "fd4d6b3533e966eec3fb33ce0f17e925fc09b512500ab7770867ebec7d7d5dc1"

inherit pypi python_setuptools3_rust cargo-update-recipe-crates ptest-python-pytest

CARGO_SRC_DIR = "rust"

require ${BPN}-crates.inc

do_install_ptest:append() {
    install -Dm 0644 ${S}/docs/usage.rst ${D}${PTEST_PATH}/docs/usage.rst
}

RDEPENDS:${PN}-ptest += " \
    python3-hypothesis \
    python3-tzdata \
    python3-unixadmin \
    python3-zoneinfo \
"
RDEPENDS:${PN} += " \
    python3-datetime \
"

CVE_PRODUCT = "agronholm:cbor2"

BBCLASSEXTEND = "native nativesdk"
