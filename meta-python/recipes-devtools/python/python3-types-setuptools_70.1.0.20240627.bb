SUMMARY = "Typing stubs for setuptools"
HOMEPAGE = "https://github.com/python/typeshed"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=6;endline=6;md5=ef4dc1e740f5c928f1608a4a9c7b578e"

inherit pypi setuptools3

SRC_URI[sha256sum] = "385907a47b5cf302b928ce07953cd91147d5de6f3da604c31905fdf0ec309e83"

BBCLASSEXTEND = "native"
