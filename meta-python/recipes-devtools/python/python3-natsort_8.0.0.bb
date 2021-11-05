SUMMARY = "Simple yet flexible natural sorting in Python."
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=58db8ac9e152dd9b700f4d39ff40a31a"

PYPI_PACKAGE = "natsort"
SRC_URI[sha256sum] = "a0a4fd71aee20a6d648da61e01180a63f7268e69983d0440bd3ad80ef1ba6981"

inherit pypi setuptools3

RDEPENDS:${PN} = "python3-fastnumbers python3-icu"
