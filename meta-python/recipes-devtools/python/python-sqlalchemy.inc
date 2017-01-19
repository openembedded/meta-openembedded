DESCRIPTION = "Python SQL toolkit and Object Relational Mapper that gives \
application developers the full power and flexibility of SQL"
HOMEPAGE = "http://www.sqlalchemy.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d7dba1721bc8ce05d421f7279cb87971"

PYPI_PACKAGE = "SQLAlchemy"
inherit pypi

SRC_URI[md5sum] = "be5af1bebe595206b71b513a14836e4f"
SRC_URI[sha256sum] = "701b57d628b9fa1cfb82f10665e7214d5d2db23251ca6f23b91c5f56fcdbdeb5"

RDEPENDS_${PN} += "${PYTHON_PN}-numbers"
