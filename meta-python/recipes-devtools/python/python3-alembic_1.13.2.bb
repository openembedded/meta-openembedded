DESCRIPTION = "A database migration tool for SQLAlchemy"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=00f8f5c8aab58c3e1cd61525a6675174"

inherit pypi setuptools3

SRC_URI[sha256sum] = "1ff0ae32975f4fd96028c39ed9bb3c867fe3af956bd7bb37343b54c9fe7445ef"

PYPI_PACKAGE = "alembic"

RDEPENDS:${PN} += "\
    python3-dateutil \
    python3-editor \
    python3-mako \
    python3-sqlalchemy \
    python3-misc \
"

BBCLASSEXTEND = "native nativesdk"
