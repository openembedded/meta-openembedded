SUMMARY  = "Yet Another Python Profiler"
HOMEPAGE = "https://github.com/sumerc/yappi"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=71c208c9a4fd864385eb69ad4caa3bee"

# 1.7.7 is tagged on GitHub but was never uploaded to PyPI
SRC_URI = "git://github.com/sumerc/yappi.git;protocol=https;branch=master;tag=v${PV} \
           file://0002-Fix-import-of-tests.utils-to-enable-pytest.patch \
           file://0003-Detect-suspended-coroutines-on-Python-3.15-and-newer.patch \
           "
SRCREV = "d6593ea0bce0a13c65bd44511c3cee858b5985e8"
UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+(\.\d+)+)$"

inherit python_setuptools_build_meta ptest-python-pytest

RDEPENDS:${PN} += "\
    python3-datetime \
    python3-pickle \
    python3-threading \
"

RDEPENDS:${PN}-ptest += " \
    python3-gevent \
    python3-multiprocessing \
    python3-pytest \
    python3-profile \
    python3-unittest-automake-output \
    python3-zopeinterface \
"

do_install_ptest:append() {
    cp -f ${S}/run_tests.py ${D}${PTEST_PATH}
}

