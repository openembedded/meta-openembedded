SUMMARY = "Extensions to the standard Python datetime module"
DESCRIPTION = "The dateutil module provides powerful extensions to the datetime module available in the Python standard library."
HOMEPAGE = "https://dateutil.readthedocs.org"
LICENSE = "BSD-3-Clause & Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3155c7bdc71f66e02678411d2abf996"

SRC_URI[md5sum] = "ba7d27d7788cc9dd04a07bd77203ffab"
SRC_URI[sha256sum] = "8f95bb7e6edbb2456a51a1fb58c8dca942024b4f5844cae62c90aa88afe6e300"

PYPI_PACKAGE = "python-dateutil"
inherit pypi

PACKAGES =+ "${PN}-zoneinfo"
FILES_${PN}-zoneinfo = "${libdir}/${PYTHON_DIR}/site-packages/dateutil/zoneinfo"

RDEPENDS_${PN}_class-target = "\
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-numbers \
    ${PYTHON_PN}-six \
    ${PYTHON_PN}-stringold \
"
