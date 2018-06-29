DESCRIPTION = "A database migration tool for SQLAlchemy"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c5f1361296835ff94bda73db7dd312a6"

SRC_URI[md5sum] = "117dac728cd081739bdbed73fe252a7d"
SRC_URI[sha256sum] = "1cd32df9a3b8c1749082ef60ffbe05ff16617b6afadfdabc680dcb9344af33d7"

PYPI_PACKAGE = "alembic"

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-dateutil \
    ${PYTHON_PN}-editor \
    ${PYTHON_PN}-mako \
    ${PYTHON_PN}-sqlalchemy \
"
