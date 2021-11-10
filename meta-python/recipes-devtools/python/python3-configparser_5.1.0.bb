SUMMARY = "This module provides the ConfigParser class which implements a basic configuration language which provides a structure similar to what's found in Microsoft Windows INI files."
SECTION = "devel/python"
HOMEPAGE = "https://docs.python.org/3/library/configparser.html"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=10;endline=10;md5=23f9ad5cad3d8cc0336e2a5d8a87e1fa"

SRC_URI[sha256sum] = "202b9679a809b703720afa2eacaad4c6c2d63196070e5d9edc953c0489dfd536"

DEPENDS += "${PYTHON_PN}-setuptools-scm-native ${PYTHON_PN}-toml-native"
inherit pypi setuptools3

BBCLASSEXTEND = "native nativesdk"
