HOMEPAGE = "https://pypi.python.org/pypi/Flask-Cors/"
SUMMARY = "A Flask extension adding a decorator for CORS support"
DESCRIPTION = "\
  A Flask extension for handling Cross Origin Resource Sharing (CORS), making cross-origin AJAX possible \
  "
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=6;endline=6;md5=134f1026f0de92fd30e71976590a2868"

CVE_PRODUCT = "flask-cors"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-setuptools-scm-native"
SRC_URI[sha256sum] = "30c5031552cd59f620ac0c8211dac45b345d3b2df310e7721879e4f46ef9c601"

RDEPENDS:${PN} += "python3-flask"
