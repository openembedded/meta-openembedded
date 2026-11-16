SUMMARY = "HTTP server for pytest to test http clients"
DESCRIPTION = "HTTP server for pytest to test http clients"
HOMEPAGE = "https://github.com/csernazs/pytest-httpserver"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7f54416ccc5478427995b27e5ef38e92"

SRC_URI[sha256sum] = "fbad14449e1acfacf24a4d6e2de3211a8654597f03e296af4a833b51836467bc"

inherit pypi python_poetry_core ptest-python-pytest

RDEPENDS:${PN}-ptest += " \
        python3-requests \
"

RDEPENDS:${PN} += " \
        python3-werkzeug \
"
