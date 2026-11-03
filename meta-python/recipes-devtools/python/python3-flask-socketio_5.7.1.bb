SUMMARY = "Socket.IO integration for Flask applications"
HOMEPAGE = "https://github.com/miguelgrinberg/Flask-SocketIO/"
SECTION = "devel/python"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=38cc21254909604298ce763a6e4440a0"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "6dcbae88b40f2ae99d0f106c751ca2e119b7ccf4a8df95cbda64f56262db99c1"

RDEPENDS:${PN} += "\
    python3-flask \
    python3-socketio \
    "
