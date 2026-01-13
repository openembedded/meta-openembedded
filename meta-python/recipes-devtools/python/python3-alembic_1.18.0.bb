DESCRIPTION = "A database migration tool for SQLAlchemy"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff111c41e8748bbfa45e8ba92347b681"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "0c4c03c927dc54d4c56821bdcc988652f4f63bf7b9017fd9d78d63f09fd22b48"

RDEPENDS:${PN} += "\
    python3-dateutil \
    python3-editor \
    python3-mako \
    python3-sqlalchemy \
    python3-misc \
"

BBCLASSEXTEND = "native nativesdk"
