SUMMARY = "Multidicts are useful for working with HTTP headers, URL query args etc."
HOMEPAGE = "https://github.com/aio-libs/multidict/"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b4fef6e4b0828c2401fb983363985b39"

inherit pypi python_setuptools_build_meta ptest-python-pytest

PV .= "+git"

SRCREV = "af07d5b2b18bb30e041983db2bea1fcb3e1c4b79"
PYPI_SRC_URI = "git://github.com/aio-libs/multidict;branch=master;protocol=https"
S = "${WORKDIR}/git"

RDEPENDS:${PN}-ptest += " \
    python3-pytest-cov \
    python3-pytest-codspeed \
"

BBCLASSEXTEND = "native nativesdk"
