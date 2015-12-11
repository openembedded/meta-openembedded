SUMMARY = "Foreign Function Interface for Python calling C code"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5677e2fdbf7cdda61d6dd2b57df547bf"
DEPENDS = "libffi python-pycparser"

SRC_URI[md5sum] = "deeba7c1fd32a66f1db587988d760c11"
SRC_URI[sha256sum] = "d45dd39a770b4afb591c82555f6a8bbc1ac7eb019eda9b621eee1a0a72201220"

inherit pypi

BBCLASSEXTEND = "native"
