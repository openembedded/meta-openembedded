DESCRIPTION = "Python API for MISP"
HOMEPAGE = "https://github.com/MISP/PyMISP"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=f64d66a9942f5e2de6c06b0dab2764b3"
PR = "r0"

SRC_URI[md5sum] = "581ec9d30e15b537dcb203f0756aa136"
SRC_URI[sha256sum] = "ba909f0d2186f10f59da383d9fccd20ce3d589b6ff243e7ea8a8ebbfce135f68"

RDEPENDS_${PN} += " ${PYTHON_PN}-requests"
