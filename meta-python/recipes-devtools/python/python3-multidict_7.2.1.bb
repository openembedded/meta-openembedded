SUMMARY = "Multidicts are useful for working with HTTP headers, URL query args etc."
DESCRIPTION = "multidict implementation"
HOMEPAGE = "https://github.com/aio-libs/multidict/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b5e9e27554f71f0f132919037cb967b2"

inherit pypi python_setuptools_build_meta ptest-python-pytest

SRC_URI[sha256sum] = "e8af3e6f1bdbc31fd762b094133b478b89988eaebc63cbe82b1d9e3bbd15cede"

RDEPENDS:${PN}-ptest += " \
    python3-objgraph \
    python3-psutil \
    python3-pytest-cov \
    python3-pytest-codspeed \
"

BBCLASSEXTEND = "native nativesdk"
