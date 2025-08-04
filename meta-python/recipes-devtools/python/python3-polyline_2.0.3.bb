SUMMARY = "A Python implementation of Google's Encoded Polyline Algorithm Format"
HOMEPAGE = "https://github.com/frederickjansen/polyline"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fb8d1dc685695195bb3c1e48adfef48"

SRC_URI[sha256sum] = "abc786b9332e84f27e6e59fd4fcbcb0c4b9af6a7f7b4175e2eb6c2786278deae"

inherit pypi python_setuptools_build_meta ptest-python-pytest

RDEPENDS:${PN} += "python3-six"

BBCLASSEXTEND = "native nativesdk"

