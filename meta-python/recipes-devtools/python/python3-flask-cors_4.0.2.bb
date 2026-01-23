HOMEPAGE = "https://pypi.python.org/pypi/Flask-Cors/"
SUMMARY = "A Flask extension adding a decorator for CORS support"
DESCRIPTION = "\
  A Flask extension for handling Cross Origin Resource Sharing (CORS), making cross-origin AJAX possible \
  "
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=118fecaa576ab51c1520f95e98db61ce"

PYPI_PACKAGE = "flask_cors"

SRC_URI[sha256sum] = "493b98e2d1e2f1a4720a7af25693ef2fe32fbafec09a2f72c59f3e475eda61d2"

inherit pypi setuptools3

RDEPENDS:${PN} += "python3-flask"
