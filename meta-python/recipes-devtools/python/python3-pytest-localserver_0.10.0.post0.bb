SUMMARY = "pytest plugin to test server connections locally."
HOMEPAGE = "https://github.com/pytest-dev/pytest-localserver"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b8da7a541d738b054dcbf70c31530432"

DEPENDS += "python3-setuptools-scm-native"

inherit pypi python_setuptools_build_meta ptest-python-pytest


SRC_URI[sha256sum] = "06c21a66609d862b42bd9cb816334820e696ea8f77cacddfe159127d48649851"

RDEPENDS:${PN}-ptest += " \
	python3-requests \
"

RDEPENDS:${PN} += " \
	python3-werkzeug \
"
