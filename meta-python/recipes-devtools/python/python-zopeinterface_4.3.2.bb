SUMMARY = "Interface definitions for Zope products"
LICENSE = "ZPL-2.1"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=e54fd776274c1b7423ec128974bd9d46"

SRC_URI[md5sum] = "5f7e15a5bcdfa3c6c0e93ffe45caf87c"
SRC_URI[sha256sum] = "6a0e224a052e3ce27b3a7b1300a24747513f7a507217fcc2a4cb02eb92945cee"

PYPI_PACKAGE = "zope.interface"
inherit pypi setuptools

RPROVIDES_${PN} += "zope-interfaces"
FILES_${PN}-dbg += "${PYTHON_SITEPACKAGES_DIR}/*.egg/*/*/.debug"
FILES_${PN}-dev += "${PYTHON_SITEPACKAGES_DIR}/zope/interface/*.c"
FILES_${PN}-doc += "${PYTHON_SITEPACKAGES_DIR}/zope/interface/*.txt"
FILES_${PN}-tests = " \
        ${PYTHON_SITEPACKAGES_DIR}/zope/interface/tests \
        ${PYTHON_SITEPACKAGES_DIR}/zope/interface/common/tests \
"
