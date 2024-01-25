SUMMARY = "Flask + marshmallow for beautiful APIs"
HOMEPAGE = "https://github.com/marshmallow-code/flask-marshmallow"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dfbd4ae0074716275fc229c775723e8f"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE = "flask_marshmallow"

SRC_URI[sha256sum] = "2d186af7c8b4455b8a2c166c7470939c17d70353671ea5a287a14676846fa013"

RDEPENDS:${PN} += "\
    python3-flask \
    python3-marshmallow \
    "
