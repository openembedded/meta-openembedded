DESCRIPTION = "Python module for Semantic Versioning"
HOMEPAGE = "https://github.com/python-semver/python-semver"
BUGTRACKER = "https://github.com/python-semver/python-semver"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3ce011bad2c5218ddd4973952a6db93a"

SRC_URI[sha256sum] = "87c113ec65f76e40fa471cce41041382cecf8516240d97e0d8b29d866a0557d1"

inherit pypi python_setuptools_build_meta ptest-python-pytest

BBCLASSEXTEND = "native nativesdk"

DEPENDS += " python3-setuptools-scm-native"

