SUMMARY = "Send file to trash natively under Mac OS X, Windows and Linux"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=05faa35ba1ca10b723f19d286c9d5237"

inherit pypi python_setuptools_build_meta ptest-python-pytest

SRC_URI += "file://run-ptest"
SRC_URI[sha256sum] = "e0ac412073f7984288908d9d8b8d13c82be69a615bb513d948fa2f053447970a"

# the macOS backend selection tests run send2trash/mac/__init__.py from the
# source tree, which isn't installed
do_install_ptest:append() {
    rm -f ${D}${PTEST_PATH}/tests/test_plat_mac.py
}

RDEPENDS:${PN} += "\
    python3-io \
    python3-datetime \
"
