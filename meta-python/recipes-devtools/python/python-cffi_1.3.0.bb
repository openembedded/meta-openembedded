SUMMARY = "Foreign Function Interface for Python calling C code"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5677e2fdbf7cdda61d6dd2b57df547bf"
DEPENDS = "libffi python-pycparser"

SRC_URI[md5sum] = "a40ed8c8ac653c8fc7d5603711b06eaf"
SRC_URI[sha256sum] = "9daa53aff0b5cf64c85c10eab7ce6776880d0ee71b78cedeae196ae82b6734e9"

inherit pypi

BBCLASSEXTEND = "native"
