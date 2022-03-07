SUMMARY = "A WSGI server for Python"
DESCRIPTION = "Waitress is meant to be a production-quality pure-Python WSGI \
    server with very acceptable performance."
HOMEPAGE = "https://github.com/Pylons/waitress"
SECTION = "devel/python"
LICENSE = "ZPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=78ccb3640dc841e1baecb3e27a6966b2"

SRC_URI[sha256sum] = "ec8a8d9b6b15f3bb2c1a82b8f3929a029c333c35fcafb08c185a9e562d8cc9c2"

inherit setuptools3 pypi
