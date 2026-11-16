DESCRIPTION = "The zope.event package provides a simple event system"

LICENSE = "ZPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=78ccb3640dc841e1baecb3e27a6966b2"

SRC_URI += "file://0001-pyproject.toml-Drop-the-setuptools-upper-bound.patch"
SRC_URI[sha256sum] = "b97d5d6327067ee6b9dfcbdf606ade9ade70991e19c162e808ea39e5fcf0f8d3"

PYPI_PACKAGE = "zope.event"
PYPI_PACKAGE_SDIST = "zope_event"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-wheel-native"
