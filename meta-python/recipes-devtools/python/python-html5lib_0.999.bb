DESCRIPTION = "HTML parser based on the WHATWG HTML specifcation"
HOMEPAGE = "https://github.com/html5lib/html5lib-python"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ba5ada9e6fead1fdc32f43c9f10ba7c"

SRCNAME = "html5lib"
SRC_URI = "http://pypi.python.org/packages/source/h/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "acb8ba4d6db5637360a07859192eb7f8"
SRC_URI[sha256sum] = "c3887f7e2875d7666107fa8bee761ff95b9391acdcc7cd1b5fd57a23b5fbc49e"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools
