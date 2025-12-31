SUMMARY = "A rough port of Node.js's EventEmitter to Python with a few tricks of its own"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b2b1cc8797dff32cec5d783148fceab5"

DEPENDS = "python3-setuptools-scm-native"
SRC_URI[sha256sum] = "b391e3c5a434d1f5118a25615001dbc8f669cf410ab67d04c4d4e07c55481c37"

inherit pypi python_setuptools_build_meta ptest-python-pytest

RDEPENDS:${PN} += "python3-typing-extensions"

PYPI_PACKAGE = "pyee"
