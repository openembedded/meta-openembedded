SUMMARY = "Extensions to the standard Python datetime module"
DESCRIPTION = "The dateutil module provides powerful extensions to the datetime module available in the Python standard library."
HOMEPAGE = "https://dateutil.readthedocs.org"
LICENSE = "Apache-2.0 AND BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3155c7bdc71f66e02678411d2abf996"

SRC_URI[sha256sum] = "d7295ff14fdc0f92f662100bfb6c551d160ca090f2939f1ec5b9794265c03f06"

PYPI_PACKAGE = "python-dateutil"

inherit pypi python_hatchling

DEPENDS += "python3-hatch-fancy-pypi-readme-native python3-hatch-vcs-native"

PACKAGES =+ "${PN}-zoneinfo"
FILES:${PN}-zoneinfo = "${PYTHON_SITEPACKAGES_DIR}/dateutil/zoneinfo"

RDEPENDS:${PN} = "\
    python3-datetime \
    python3-numbers \
    python3-stringold \
    python3-tzdata \
    python3-zoneinfo \
"

BBCLASSEXTEND = "native nativesdk"
