DESCRIPTION = "A database migration tool for SQLAlchemy"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bd64aba1b968c2bfbc2b525a181ce85c"

inherit pypi setuptools3

SRC_URI[md5sum] = "758b28361557e45c79c454d662131a83"
SRC_URI[sha256sum] = "2df2519a5b002f881517693b95626905a39c5faf4b5a1f94de4f1441095d1d26"

PYPI_PACKAGE = "alembic"

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-dateutil \
    ${PYTHON_PN}-editor \
    ${PYTHON_PN}-mako \
    ${PYTHON_PN}-sqlalchemy \
"
