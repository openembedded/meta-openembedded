SUMMARY = "A Python implementation of Google's Encoded Polyline Algorithm Format"
HOMEPAGE = "https://github.com/frederickjansen/polyline"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fb8d1dc685695195bb3c1e48adfef48"

SRC_URI[sha256sum] = "92deedee05d52918e83cf7607011ba1cc52907932e7228ba6e28f4a0a8c6370d"

inherit pypi python_setuptools_build_meta ptest-python-pytest

BBCLASSEXTEND = "native nativesdk"

