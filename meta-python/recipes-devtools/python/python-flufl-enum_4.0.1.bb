SUMMARY  = "A Python enumeration package."
DESCRIPTION = "The flufl.enum library is a Python enumeration package. Its goal is to provide simple, \
specific, concise semantics in an easy to read and write syntax. flufl.enum has just enough of the \
features needed to make enumerations useful, but without a lot of extra baggage to weigh them down. "

LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=1fa25baed15d3be23c902636379438e8"

SRC_URI[md5sum] = "b3ad23761a78232bc78499ced7cb85d9"
SRC_URI[sha256sum] = "4af7e7cf2dcc7517251570c7ef9ad194e30ee4b6f860eba500c03954ae95f9d8"

PYPI_PACKAGE = "flufl.enum"
inherit pypi setuptools

PACKAGES =+ "\  
    ${PN}-test \
"

FILES_${PN}-doc += "\
    ${libdir}/${PYTHON_DIR}/site-packages/flufl/enum/*.rst \
    ${libdir}/${PYTHON_DIR}/site-packages/flufl/enum/docs \
"

FILES_${PN}-test += "${libdir}/${PYTHON_DIR}/site-packages/flufl/enum/tests"
