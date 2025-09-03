DESCRIPTION = "A database migration tool for SQLAlchemy"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d2ee18d90dcc02d96b76e9e953629936"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "a88bb7f6e513bd4301ecf4c7f2206fe93f9913f9b48dac3b78babde2d6fe765e"

RDEPENDS:${PN} += "\
    python3-dateutil \
    python3-editor \
    python3-mako \
    python3-sqlalchemy \
    python3-misc \
"

BBCLASSEXTEND = "native nativesdk"
