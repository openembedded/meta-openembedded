SUMMARY = "Ctypes-based simple MagickWand API binding for Python"
HOMEPAGE = "https://docs.wand-py.org/"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2a2b05e6331064556e971cfa0efca0bf"

SRC_URI += "file://run-ptest"

SRC_URI[sha256sum] = "0ecec0f972cb82128a0d2918b9fcc1d1bce04d5b54f590839943df31e55e5274"

inherit pypi python_setuptools_build_meta ptest


FILES:${PN}-doc += "${datadir}/README.rst"

RDEPENDS:${PN} += " \
    imagemagick \
"

RDEPENDS:${PN}-ptest += " \
    python3-pytest \
    python3-core \
    python3-datetime \
    python3-numbers \
    python3-unittest \
    python3-unittest-automake-output \
"

do_install_ptest() {
    install -d ${D}${PTEST_PATH}/tests
    cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
    cp -f ${S}/pyproject.toml ${D}${PTEST_PATH}/
}

BBCLASSEXTEND = "native nativesdk"
