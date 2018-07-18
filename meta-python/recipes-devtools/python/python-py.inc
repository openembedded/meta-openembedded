SUMMARY = "Library with cross-python path, ini-parsing, io, code, log facilities"
HOMEPAGE = "http://py.readthedocs.io/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a6bb0320b04a0a503f12f69fea479de9"

SRC_URI[md5sum] = "7502d66fa68ea4ae5b61c511cd177d6a"
SRC_URI[sha256sum] = "3fd59af7435864e1a243790d322d763925431213b6b8529c6ca71081ace3bbf7"

DEPENDS += "${PYTHON_PN}-setuptools-scm-native"
BBCLASSEXTEND = "native nativesdk"
