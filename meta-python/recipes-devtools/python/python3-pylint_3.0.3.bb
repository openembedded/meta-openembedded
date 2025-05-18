SUMMARY="Pylint is a Python source code analyzer"
HOMEPAGE= "http://www.pylint.org/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c107cf754550e65755c42985a5d4e9c9"

SRC_URI += "git://github.com/pylint-dev/pylint;branch=maintenance/3.0.x;protocol=https \
           file://run-ptest \
           "
SRCREV = "1a5ffc1f447b77071ffe18a9c6836c09147ee2ed"

inherit python_setuptools_build_meta ptest

RDEPENDS:${PN} += "${PYTHON_PN}-astroid \
                   ${PYTHON_PN}-dill \
                   ${PYTHON_PN}-isort \
                   ${PYTHON_PN}-mccabe \
                   ${PYTHON_PN}-numbers \
                   ${PYTHON_PN}-platformdirs \
                   ${PYTHON_PN}-shell \
                   ${PYTHON_PN}-json \
                   ${PYTHON_PN}-pkgutil \
                   ${PYTHON_PN}-difflib \
                   ${PYTHON_PN}-netserver \
                   ${PYTHON_PN}-tomlkit \
                  "

RDEPENDS:${PN}-ptest += " \
        ${PYTHON_PN}-core \
        ${PYTHON_PN}-git \
        ${PYTHON_PN}-py \
        ${PYTHON_PN}-pytest \
        ${PYTHON_PN}-pytest-benchmark \
        ${PYTHON_PN}-pytest-runner \
        ${PYTHON_PN}-pytest-timeout \
        ${PYTHON_PN}-pytest-xdist \
        ${PYTHON_PN}-requests \
        ${PYTHON_PN}-statistics \
        ${PYTHON_PN}-tomllib \
        ${PYTHON_PN}-typing-extensions \
        "

S = "${WORKDIR}/git"

do_install_ptest() {
    install -d ${D}${PTEST_PATH}/tests
    cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
    install -Dm 0644 ${S}/tests/.pylint_primer_tests/.gitkeep ${D}${PTEST_PATH}/tests/.pylint_primer_tests/.gitkeep
    sed -i 's#/usr/bin/python$#/usr/bin/python3#g' ${D}${PTEST_PATH}/tests/data/ascript
    # regression_distutil_import_error_73.py fails to run see 
    # https://lists.openembedded.org/g/openembedded-devel/topic/103181847
    rm ${D}${PTEST_PATH}/tests/functional/r/regression_02/regression_distutil_import_error_73.py
}

BBCLASSEXTEND = "native nativesdk"
