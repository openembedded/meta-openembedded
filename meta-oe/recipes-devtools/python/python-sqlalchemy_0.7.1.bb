DESCRIPTION = "Python SQL toolkit and Object Relational Mapper that gives \
application developers the full power and flexibility of SQL"
HOMEPAGE = "http://www.sqlalchemy.org/"
LICENSE = "MIT"         
LIC_FILES_CHKSUM = "file://LICENSE;md5=e88f48967a8bd1fe88f5425f7e659dbf"
RDEPENDS_${PN} += "python-numbers"
PR = "r1"

SRCNAME = "SQLAlchemy"
SRC_URI = "${SOURCEFORGE_MIRROR}/sqlalchemy/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "76e2e1e48b8047f1ca7023f143374aef"
SRC_URI[sha256sum] = "eedd2d63d6404d9bbe3c4c6a63a5442d2c2650b862f9c10f721cfae4a735d05a"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools
