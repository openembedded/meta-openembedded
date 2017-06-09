SUMMARY = "A high-level Python Web framework"
HOMEPAGE = "http://www.djangoproject.com/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f09eb47206614a4954c51db8a94840fa"

SRC_URI[md5sum] = "f089f1f86d25f2b78f6cf36478d4edd1"
SRC_URI[sha256sum] = "3c5b070482df4f9e5750539dc1824d353729ee423fd410c579b8cd3dea5b0617"

PYPI_PACKAGE = "Django"
inherit pypi

FILES_${PN} += "${datadir}/django"

BBCLASSEXTEND = "nativesdk"

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-importlib \
    ${PYTHON_PN}-numbers \
    ${PYTHON_PN}-pip \
    ${PYTHON_PN}-pkgutil \
    ${PYTHON_PN}-subprocess \
    ${PYTHON_PN}-threading \
    "
