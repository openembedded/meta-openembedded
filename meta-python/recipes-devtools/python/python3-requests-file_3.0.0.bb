SUMMARY = "File transport adapter for Requests"
HOMEPAGE = "https://github.com/dashea/requests-file"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9cc728d6087e43796227b0a31422de6b"

SRC_URI[sha256sum] = "68789589cfde7098e8933fe3e69bbd864f7f0c22f118937b424d94d0e1b7760f"

PYPI_PACKAGE = "requests_file"
UPSTREAM_CHECK_PYPI_PACKAGE = "${PYPI_PACKAGE}"

inherit pypi python_setuptools_build_meta ptest-python-pytest

DEPENDS += "python3-setuptools-scm-native"

RDEPENDS:${PN} += " \
    python3-requests \
"

