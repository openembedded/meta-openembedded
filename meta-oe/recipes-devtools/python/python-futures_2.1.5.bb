DESCRIPTION = "The concurrent.futures module provides a high-level interface for asynchronously executing callables."
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=e96d16356742ac95c07be571717e51a3"
HOMEPAGE = "http://code.google.com/p/pythonfutures"
DEPENDS = "python"

SRC_URI = "https://pypi.python.org/packages/source/f/futures/futures-${PV}.tar.gz"
SRC_URI[md5sum] = "2fc924890ccd30a5fe676fa9bbcf0ab8"
SRC_URI[sha256sum] = "8f5a627d3aee94cc1859a942965fdebb714be8cdd2366d819cb8fb9b7cc628a6"

S = "${WORKDIR}/futures-${PV}"

inherit setuptools
