SUMMARY = "Foreign Function Interface for Python calling C code"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5677e2fdbf7cdda61d6dd2b57df547bf"
DEPENDS = "libffi python-pycparser"

SRC_URI[md5sum] = "34122a545060cee58bab88feab57006d"
SRC_URI[sha256sum] = "6ed5dd6afd8361f34819c68aaebf9e8fc12b5a5893f91f50c9e50c8886bb60df"

inherit pypi setuptools

BBCLASSEXTEND = "native"
