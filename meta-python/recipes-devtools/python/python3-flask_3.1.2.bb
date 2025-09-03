SUMMARY = "A microframework based on Werkzeug, Jinja2 and good intentions"
DESCRIPTION = "\
Flask is a microframework for Python based on Werkzeug, Jinja 2 and good \
intentions. And before you ask: It’s BSD licensed!"
HOMEPAGE = "https://github.com/pallets/flask"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ffeffa59c90c9c4a033c7574f8f3fb75"

SRC_URI[sha256sum] = "bf656c15c80190ed628ad08cdfd3aaa35beb087855e2f494910aa3774cc4fd87"

inherit pypi python_flit_core ptest-python-pytest

CLEANBROKEN = "1"

RDEPENDS:${PN} = " \
    python3-blinker \
    python3-click \
    python3-importlib-metadata \
    python3-itsdangerous \
    python3-jinja2 \
    python3-werkzeug \
"

RDEPENDS:${PN}-ptest += "\
    python3-asgiref \
"
