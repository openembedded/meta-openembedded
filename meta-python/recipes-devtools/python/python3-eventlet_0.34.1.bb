DESCRIPTION = "Highly concurrent networking library"
HOMEPAGE = "http://pypi.python.org/pypi/eventlet"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=56472ad6de4caf50e05332a34b66e778"

SRC_URI[sha256sum] = "0087763f3ae18a571a5cc60fdd06ceb02fdb9f006693e9ee1e8e8044ae3b470b"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
	${PYTHON_PN}-dnspython \
	${PYTHON_PN}-six \
	${PYTHON_PN}-greenlet \
"
