DESCRIPTION = "Python HTTP for Humans."
HOMEPAGE = "http://python-requests.org"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=58c7e163c9f8ee037246da101c6afd1e"

SRCNAME = "requests"

SRC_URI = "http://pypi.python.org/packages/source/r/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "29b173fd5fa572ec0764d1fd7b527260"
SRC_URI[sha256sum] = "398a3db6d61899d25fd4a06c6ca12051b0ce171d705decd7ed5511517b4bb93d"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

RDEPENDS_${PN} = "python-email python-json python-netserver python-zlib"
