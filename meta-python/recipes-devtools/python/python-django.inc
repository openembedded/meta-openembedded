SUMMARY = "A high-level Python Web framework"
HOMEPAGE = "http://www.djangoproject.com/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f09eb47206614a4954c51db8a94840fa"

# From 4.2.21 onwards package name changed to "django". This common include file
# is used across 2.x, 3.x and 4.x variants hence making this as a weak variable 
# which can be overwritten in 4.x recipe
PYPI_PACKAGE ?= "Django"

inherit pypi

UPSTREAM_CHECK_REGEX = "(?P<pver>\d+(\.\d+)+)/"

FILES:${PN} += "${datadir}/django"

BBCLASSEXTEND = "native nativesdk"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-compression \
    ${PYTHON_PN}-ctypes \
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-email \
    ${PYTHON_PN}-html \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-multiprocessing \
    ${PYTHON_PN}-netserver \
    ${PYTHON_PN}-numbers \
    ${PYTHON_PN}-pkgutil \
    ${PYTHON_PN}-pytz \
    ${PYTHON_PN}-threading \
    ${PYTHON_PN}-unixadmin \
    ${PYTHON_PN}-xml \
    ${PYTHON_PN}-distutils \
"

CVE_PRODUCT = "django"
# cpe-incorrect: this is for gofiber:django, some go-project, not python-django
CVE_CHECK_IGNORE += "CVE-2024-22199"
