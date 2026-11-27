DESCRIPTION = "Python module for Semantic Versioning"
HOMEPAGE = "https://github.com/python-semver/python-semver"
BUGTRACKER = "https://github.com/python-semver/python-semver"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3ce011bad2c5218ddd4973952a6db93a"

SRC_URI[sha256sum] = "adf2c822d2d8908a1dce0121da7da6511851fbd33080e61da8d2ba1e0677e8f3"

inherit pypi python_setuptools_build_meta ptest-python-pytest

BBCLASSEXTEND = "native nativesdk"

DEPENDS += " python3-setuptools-scm-native"

