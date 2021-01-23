SUMMARY = "Modified fork of CPython's ast module that parses `# type:` comments"
HOMEPAGE = "https://github.com/python/typed_ast"
LICENSE = "Apache-2.0 & MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=97f1494e93daf66a5df47118407a4c4f"

PYPI_PACKAGE = "typed_ast"

inherit pypi setuptools3

SRC_URI[sha256sum] = "8c8aaad94455178e3187ab22c8b01a3837f8ee50e09cf31f1ba129eb293ec30b"

BBCLASSEXTEND = "native"
