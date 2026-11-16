SUMMARY = "A rough port of Node.js's EventEmitter to Python with a few tricks of its own"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b2b1cc8797dff32cec5d783148fceab5"

DEPENDS = "python3-setuptools-scm-native"
SRC_URI[sha256sum] = "fe092d3a2a66f0f6a277fb227b078d3d849318da9a9d5d2f6576143236e84e48"

inherit pypi python_hatchling ptest-python-pytest

DEPENDS += "python3-wheel-native"

RDEPENDS:${PN} += "python3-typing-extensions"
