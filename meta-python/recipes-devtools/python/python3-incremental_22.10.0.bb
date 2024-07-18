DESCRIPTION = "Incremental is a small library that versions your Python projects"
HOMEPAGE = "https://github.com/twisted/incremental"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6ca9b07f08e2c72d48c74d363d1e0e15"

SRC_URI[sha256sum] = "912feeb5e0f7e0188e6f42241d2f450002e11bbc0937c65865045854c24c0bd0"

inherit pypi python_setuptools_build_meta

PACKAGE_BEFORE_PN = "\
    ${PN}-scripts \
    ${PN}-tests \
"

FILES:${PN}-scripts = "\
    ${PYTHON_SITEPACKAGES_DIR}/incremental/update.py \
    ${PYTHON_SITEPACKAGES_DIR}/incremental/__pycache__/update*.pyc \
"

RDEPENDS:${PN}-scripts = "\
    python3-click \
    python3-twisted-core \
"

FILES:${PN}-tests = "${PYTHON_SITEPACKAGES_DIR}/incremental/tests"

# The tests require unit testing tool 'trial' from the twisted package
RDEPENDS:${PN}-tests = "\
    ${PN}-scripts \
    python3-twisted \
"

BBCLASSEXTEND = "native"

