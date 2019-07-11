SUMMARY = "A super-fast templating language that borrows the best ideas from the existing templating languages"
HOMEPAGE = "http://www.makotemplates.org/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=df7e6c7c82990acf0228a55e00d29bc9"

PYPI_PACKAGE = "Mako"

inherit pypi

SRC_URI[md5sum] = "ad6c7dcb5d39c99f4fdf4fec892c5dee"
SRC_URI[sha256sum] = "95ee720cc3453063788515d55bd7ce4a2a77b7b209e4ac70ec5c86091eb02541"

RDEPENDS_${PN} = " \
    ${PYTHON_PN}-html \
    ${PYTHON_PN}-netclient \
    ${PYTHON_PN}-shell \
    ${PYTHON_PN}-threading \
"

BBCLASSEXTEND = "native nativesdk"
