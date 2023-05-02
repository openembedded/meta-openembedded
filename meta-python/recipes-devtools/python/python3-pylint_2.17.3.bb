SUMMARY="Pylint is a Python source code analyzer"
HOMEPAGE= "http://www.pylint.org/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c107cf754550e65755c42985a5d4e9c9"

SRC_URI += " \
        git://github.com/pylint-dev/pylint;branch=main;protocol=https \
        file://run-ptest \
        file://0001-pyproject.toml-Remove-hard-coded-dependencies.patch \
        "
SRCREV ?= "389e14c36819cb87190fd412d3f366a3283f0078"

inherit python_setuptools_build_meta ptest

RDEPENDS:${PN} += "${PYTHON_PN}-astroid \
                   ${PYTHON_PN}-isort \
                   ${PYTHON_PN}-numbers \
                   ${PYTHON_PN}-shell \
                   ${PYTHON_PN}-json \
                   ${PYTHON_PN}-pkgutil \
                   ${PYTHON_PN}-difflib \
                   ${PYTHON_PN}-netserver \
                  "

RDEPENDS:${PN}-ptest += " \
        ${PYTHON_PN}-core \
        ${PYTHON_PN}-dill \
        ${PYTHON_PN}-git \
        ${PYTHON_PN}-platformdirs \
        ${PYTHON_PN}-pytest \
        ${PYTHON_PN}-pytest-benchmark \
        ${PYTHON_PN}-statistics \
        ${PYTHON_PN}-tomlkit \
        ${PYTHON_PN}-tomllib \
        "

S = "${WORKDIR}/git"

do_install_ptest() {
    install -d ${D}${PTEST_PATH}/tests
    cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
    sed -i 's#/usr/bin/python$#/usr/bin/python3#g' ${D}${PTEST_PATH}/tests/data/ascript
}
