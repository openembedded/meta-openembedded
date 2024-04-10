SUMMARY = "Parsing made fun ... using typing."
HOMEPAGE = "https://github.com/hgrecco/flexparser"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=32f547dac365c355d2cdbcd7ebea9144"

DEPENDS += "python3-setuptools-scm-native"
SRC_URI[sha256sum] = "692e7524d9e14b2b1231b772b091d7d6296951deb383f5a67bfbd0ecb0b9fa9a"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE = "flexparser"
