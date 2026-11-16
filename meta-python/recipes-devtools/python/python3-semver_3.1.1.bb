DESCRIPTION = "Python module for Semantic Versioning"
HOMEPAGE = "https://github.com/python-semver/python-semver"
BUGTRACKER = "https://github.com/python-semver/python-semver"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3ce011bad2c5218ddd4973952a6db93a"

SRC_URI[sha256sum] = "149b26cabada6f63212f97e94505d21578cb1d280cf714827f328d5e70765fea"

inherit pypi python_setuptools_build_meta ptest-python-pytest

BBCLASSEXTEND = "native nativesdk"

DEPENDS += " python3-setuptools-scm-native"

