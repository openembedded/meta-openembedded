SUMMARY = "Socket.IO integration for Flask applications"
HOMEPAGE = "https://github.com/miguelgrinberg/Flask-SocketIO/"
SECTION = "devel/python"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=38cc21254909604298ce763a6e4440a0"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "aa01e50106962808dadc245407c3b6354295b1526e888bd154dc7ff4801a7c88"

RDEPENDS:${PN} += "\
    python3-flask \
    python3-socketio \
    "
