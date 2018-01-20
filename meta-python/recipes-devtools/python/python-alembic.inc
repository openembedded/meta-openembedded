DESCRIPTION = "A database migration tool for SQLAlchemy"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c5f1361296835ff94bda73db7dd312a6"

SRC_URI[md5sum] = "3e82bbe325464fd542ab4cc44929c813"
SRC_URI[sha256sum] = "46f4849c6dce69f54dd5001b3215b6a983dee6b17512efee10e237fa11f20cfa"

PYPI_PACKAGE = "alembic"

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-dateutil \
    ${PYTHON_PN}-editor \
    ${PYTHON_PN}-mako \
    ${PYTHON_PN}-sqlalchemy \
"
