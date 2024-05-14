SUMMARY = "Typing stubs for psutil"
HOMEPAGE = "https://github.com/python/typeshed"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=6;endline=6;md5=ef4dc1e740f5c928f1608a4a9c7b578e"

inherit pypi setuptools3

SRC_URI[sha256sum] = "2c0b22edab6c8775f4a8688e3f14cefda8793e26ddf99d61b654a0d600179087"

BBCLASSEXTEND = "native"
