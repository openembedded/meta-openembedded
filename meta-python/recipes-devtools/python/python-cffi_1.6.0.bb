SUMMARY = "Foreign Function Interface for Python calling C code"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5677e2fdbf7cdda61d6dd2b57df547bf"
DEPENDS = "libffi python-pycparser"

SRC_URI[md5sum] = "2fae9160991afefb20ff0fbde3b14faf"
SRC_URI[sha256sum] = "a7f75c4ef2362c0a0e54657add0a6c509fecbfa3b3807bc0925f5cb1c9f927db"
PYPI_PACKAGE_HASH = "b69811feff87072e2e640fb8320712b781eccdef05d588618915236b32289d5a"

inherit pypi setuptools

BBCLASSEXTEND = "native"
