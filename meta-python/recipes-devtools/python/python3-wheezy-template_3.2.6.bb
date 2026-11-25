SUMMARY = "a lightweight template library"
HOMEPAGE = "https://github.com/akornatskyy/wheezy.template"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fa10554c46b94944529c6a886cf85631"

SRC_URI[sha256sum] = "a00429a7bd1cb83fb6a4a5b67c486a08c8ac5a58e96bc9de7d806f9b1918f7df"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-wheel-native"

BBCLASSEXTEND = "native"
