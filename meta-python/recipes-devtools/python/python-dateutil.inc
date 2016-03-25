SUMMARY = "Extensions to the standard Python datetime module"
DESCRIPTION = "The dateutil module provides powerful extensions to the datetime module available in the Python standard library."
HOMEPAGE = "https://dateutil.readthedocs.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=51430b33c900f45f999c459ee29ca493"
PR = "r0"

SRC_URI[md5sum] = "2769f13c596427558136b34977a95269"
SRC_URI[sha256sum] = "40d1bc468c7df50aff9e7a12c14687f9180efcff86900ee2963f9f2c13b5d7a9"

PYPI_PACKAGE = "python-dateutil"
inherit pypi 

PACKAGES =+ "${PN}-zoneinfo"
FILES_${PN}-zoneinfo = "${libdir}/${PYTHON_DIR}/site-packages/dateutil/zoneinfo"

RDEPENDS_${PN} = "\
    ${PYTHON_PN}-core \
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-six \
    "
