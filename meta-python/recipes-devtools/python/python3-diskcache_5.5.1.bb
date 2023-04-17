DESCRIPTION = "Disk Cache -- Disk and file backed persistent cache."
HOMEPAGE = "http://www.grantjenks.com/docs/diskcache/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c60ef82f0f40155453f6d5f2c94b6e8e"

SRC_URI[sha256sum] = "8685515584cc110a463d0728d98beaba56c7e2803b60a19cb8983a4c9e341d91"

PYPI_PACKAGE = "diskcache"

inherit pypi setuptools3

RDEPENDS:${PN} = "\
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-pickle \
    ${PYTHON_PN}-sqlite3 \
    ${PYTHON_PN}-core \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-compression \
    ${PYTHON_PN}-threading \
"

CLEANBROKEN = "1"
