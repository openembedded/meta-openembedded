SUMMARY = "Foreign Function Interface for Python calling C code"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5677e2fdbf7cdda61d6dd2b57df547bf"
DEPENDS = "libffi python-pycparser"

SRC_URI[md5sum] = "b1bf4625ae07a8a932f2f1a2eb200c54"
SRC_URI[sha256sum] = "1988ce7ff9c64ecd5077776175e90fd8f0a8c827cb241a23647175ce08126bb2"

inherit pypi

BBCLASSEXTEND = "native"
