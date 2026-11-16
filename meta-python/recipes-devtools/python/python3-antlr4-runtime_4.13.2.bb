SUMMARY = "ANTLR runtime for Python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=cd70187b2f2f288c20e09a358ce52b85"

SRC_URI[sha256sum] = "909b647e1d2fc2b70180ac586df3933e38919c85f98ccc656a96cd3f25ef3916"

PYPI_PACKAGE = "antlr4-python3-runtime"

inherit pypi python_setuptools_build_meta

BBCLASSEXTEND = "nativesdk native"
