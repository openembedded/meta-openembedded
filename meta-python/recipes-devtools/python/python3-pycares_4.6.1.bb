SUMMARY = "Python interface for c-ares"
DESCRIPTION = "\
pycares is a Python module which provides an interface to c-ares. c-ares is \
a C library that performs DNS requests and name resolutions asynchronously."
HOMEPAGE = "https://github.com/saghul/pycares"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b1538fcaea82ebf2313ed648b96c69b1"

SRC_URI[sha256sum] = "8a1d981206a16240eedc79b51af3293575715d4b0b971f4eb47e24839d5ab440"

PYPI_PACKAGE = "pycares"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-cffi-native"

RDEPENDS:${PN} += " \
    python3-cffi \
    python3-idna \
"

BBCLASSEXTEND = "native nativesdk"
