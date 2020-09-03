SUMMARY = "Traitlets Python config system"
HOMEPAGE = "http://ipython.org"
AUTHOR = "IPython Development Team <ipython-dev@scipy.org>"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING.md;md5=eec4de4d599518742e54e75954e33b46"

PYPI_PACKAGE = "traitlets"

SRC_URI[md5sum] = "66b76cfffd590c39861bc11c247725a5"
SRC_URI[sha256sum] = "a2e91709a0330b6c5d497ed470b2feb1ed8da5c9dd807c6daab41f727b9391c9"

RDEPENDS_${PN} = "\
    ${PYTHON_PN}-ipython-genutils \
    ${PYTHON_PN}-decorator \
"

inherit setuptools3 pypi
