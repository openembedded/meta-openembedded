SUMMARY = "Python Remote Objects"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c1c9ccd5f4ca5d0f5057c0e690a0153d"

SRC_URI[sha256sum] = "82c3dfc9860b49f897b28ff24fe6716c841672c600af8fe40d0e3a7fac9a3f5e"

PYPI_PACKAGE = "Pyro5"
UPSTREAM_CHECK_PYPI_PACKAGE = "${PYPI_PACKAGE}"

inherit pypi setuptools3 ptest-python-pytest

RDEPENDS:${PN} += " \
    python3-json \
    python3-logging \
    python3-serpent \
    python3-threading \
    "

RDEPENDS:${PN}-ptest += " \
    python3-html \
    python3-misc \
    python3-sqlite3 \
    "

do_install_ptest:append(){
    cp -r ${S}/certs ${D}${PTEST_PATH}/
}
