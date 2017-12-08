DESCRIPTION = "A database migration tool for SQLAlchemy"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d07407716fd24408b5747b0fa2262775"

SRC_URI[md5sum] = "fcb096bccc87c8770bd07a04606cb989"
SRC_URI[sha256sum] = "042851ebe9efa07be6dc1395b1793b6c1d8964a39b73a0ce1649e2bcd41ea732"

PYPI_PACKAGE = "alembic"

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-editor \
    ${PYTHON_PN}-sqlalchemy \
    "
