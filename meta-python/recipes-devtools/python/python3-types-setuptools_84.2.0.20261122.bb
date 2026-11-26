SUMMARY = "Typing stubs for setuptools"
HOMEPAGE = "https://github.com/python/typeshed"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=6;endline=6;md5=320eb2c24b8d2e890b6800000ce9352c"

inherit pypi python_setuptools_build_meta


SRC_URI[sha256sum] = "5c513edc18ac4e5feb11204c379e15c26b849127f569e8d5284e380986cbdcc8"

BBCLASSEXTEND = "native"
