SUMMARY = "Typing stubs for psutil"
HOMEPAGE = "https://github.com/python/typeshed"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=6;endline=6;md5=c2d9643b4523fdf462545aeb1356ad23"

inherit pypi setuptools3

SRC_URI[sha256sum] = "5f5c71d02f7a018249d457e080f85966a31a8200644c5459f63cf02be1d85c04"

BBCLASSEXTEND = "native"
