SUMMARY  = "A Python enumeration package."
DESCRIPTION = "The flufl.enum library is a Python enumeration package. Its goal is to provide simple, \
specific, concise semantics in an easy to read and write syntax. flufl.enum has just enough of the \
features needed to make enumerations useful, but without a lot of extra baggage to weigh them down. "
HOMEPAGE = "https://pypi.python.org/pypi/flufl.enum"
SECTION = "devel/python"

LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=1fa25baed15d3be23c902636379438e8"

SRCNAME = "flufl.enum"

SRC_URI = " \
    http://pypi.python.org/packages/source/f/${SRCNAME}/${SRCNAME}-${PV}.tar.gz \
"

SRC_URI[md5sum] = "b3ad23761a78232bc78499ced7cb85d9"
SRC_URI[sha256sum] = "4af7e7cf2dcc7517251570c7ef9ad194e30ee4b6f860eba500c03954ae95f9d8"

inherit setuptools

S = "${WORKDIR}/${SRCNAME}-${PV}"

PACKAGES =+ "\  
    ${PN}-test \
"

FILES_${PN}-doc += "\
    ${libdir}/${PYTHON_DIR}/site-packages/flufl/enum/*.rst \
    ${libdir}/${PYTHON_DIR}/site-packages/flufl/enum/docs \
"

FILES_${PN}-test += "${libdir}/${PYTHON_DIR}/site-packages/flufl/enum/tests"
