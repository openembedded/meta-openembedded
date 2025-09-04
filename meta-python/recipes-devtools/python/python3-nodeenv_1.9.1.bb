SUMMARY = "Node.js virtual environment builder"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a9e238ffae5bb6447dbac6291e1dc3a3"

PYPI_PACKAGE = "nodeenv"

inherit pypi python_setuptools_build_meta
SRC_URI[sha256sum] = "6ec12890a2dab7946721edbfbcd91f3319c6ccc9aec47be7c7e6b7011ee6645f"

DEPENDS += "python3-setuptools-scm-native"
