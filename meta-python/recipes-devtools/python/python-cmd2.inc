SUMMARY = "Extra features for standard library's cmd module"
HOMEPAGE = "http://packages.python.org/cmd2/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=01aeabea7ae1529a8e4b674b7107d6bc"

SRC_URI[md5sum] = "0e1ce6a2fd8846c2a9e0de2ceb1fa461"
SRC_URI[sha256sum] = "8880fea501bb45a42e8ddde18023d336ccf72f187c4b03e9ee9f13d719ecbd5f"

inherit pypi

DEPENDS += "${PYTHON_PN}-setuptools-scm-native"

BBCLASSEXTEND = "native nativesdk"
