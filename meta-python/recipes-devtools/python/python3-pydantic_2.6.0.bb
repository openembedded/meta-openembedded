SUMMARY = "Data validation using Python type hinting"
DESCRIPTION = "Data validation and settings management using Python \
type hints.\
\
Fast and extensible, Pydantic plays nicely with your linters/IDE/brain. \
Define how data should be in pure, canonical Python 3.7+; validate it with \
Pydantic."
HOMEPAGE = "https://github.com/samuelcolvin/pydantic"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=09280955509d1c4ca14bae02f21d49a6"

inherit pypi python_hatchling

SRC_URI[sha256sum] = "ae887bd94eb404b09d86e4d12f93893bdca79d766e738528c6fa1c849f3c6bcf"

DEPENDS += "python3-hatch-fancy-pypi-readme-native"

RDEPENDS:${PN} += "\
    python3-annotated-types \
    python3-core \
    python3-datetime \
    python3-image \
    python3-io \
    python3-json \
    python3-logging \
    python3-netclient \
    python3-numbers \
    python3-profile \
    python3-pydantic-core \
    python3-typing-extensions \
"

inherit ptest
SRC_URI += "file://run-ptest"
RDEPENDS:${PN}-ptest += "\
    python3-cloudpickle \
    python3-dirty-equals \
    python3-pytest \
    python3-pytest-mock \
    python3-unittest-automake-output \
"

do_install_ptest() {
    cp -rf ${S}/tests/ ${D}${PTEST_PATH}/
    # Requires 'ruff' (python3-ruff) which we cannot build
    # until we have Rust 1.71+ in oe-core
    rm -f ${D}${PTEST_PATH}/tests/test_docs.py
    # We are not trying to support mypy
    rm -f ${D}${PTEST_PATH}/tests/test_mypy.py
    # We are not trying to run benchmarks
    rm -rf ${D}${PTEST_PATH}/tests/benchmarks
}

