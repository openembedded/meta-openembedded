DESCRIPTION = "A database migration tool for SQLAlchemy"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d2ee18d90dcc02d96b76e9e953629936"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "4652a0b3e19616b57d652b82bfa5e38bf5dbea0813eed971612671cb9e90c0fe"

RDEPENDS:${PN} += "\
    python3-dateutil \
    python3-editor \
    python3-mako \
    python3-sqlalchemy \
    python3-misc \
"

BBCLASSEXTEND = "native nativesdk"
