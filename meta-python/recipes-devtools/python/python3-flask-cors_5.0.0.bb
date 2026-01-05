HOMEPAGE = "https://pypi.python.org/pypi/Flask-Cors/"
SUMMARY = "A Flask extension adding a decorator for CORS support"
DESCRIPTION = "\
  A Flask extension for handling Cross Origin Resource Sharing (CORS), making cross-origin AJAX possible \
  "
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=118fecaa576ab51c1520f95e98db61ce"

PYPI_PACKAGE = "flask_cors"
UPSTREAM_CHECK_PYPI_PACKAGE = "${PYPI_PACKAGE}"

CVE_PRODUCT = "flask-cors"

inherit pypi setuptools3
SRC_URI[sha256sum] = "5aadb4b950c4e93745034594d9f3ea6591f734bb3662e16e255ffbf5e89c88ef"

RDEPENDS:${PN} += "python3-flask"
