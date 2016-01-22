SUMMARY = "Extensions to the standard Python datetime module"
DESCRIPTION = "The dateutil module provides powerful extensions to the datetime module available in the Python standard library."
HOMEPAGE = "https://dateutil.readthedocs.org"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=51430b33c900f45f999c459ee29ca493"
PR = "r0"

SRC_URI = "https://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "4ef68e1c485b09e9f034e10473e5add2"
SRC_URI[sha256sum] = "3e95445c1db500a344079a47b171c45ef18f57d188dffdb0e4165c71bea8eb3d"

SRCNAME = "python-dateutil"
S = "${WORKDIR}/${SRCNAME}-${PV}"

PACKAGES =+ "${PN}-zoneinfo"
FILES_${PN}-zoneinfo = "${libdir}/${PYTHON_DIR}/site-packages/dateutil/zoneinfo"

RDEPENDS_${PN} = "\
    ${PYTHON_PN}-core \
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-six \
    "
