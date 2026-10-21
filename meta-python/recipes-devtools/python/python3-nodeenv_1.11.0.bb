SUMMARY = "Node.js virtual environment builder"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a9e238ffae5bb6447dbac6291e1dc3a3"


inherit pypi python_setuptools_build_meta
SRC_URI[sha256sum] = "3ce8fe5b71d16e8af7039ca65257354100bc772965d6bc549070649e53b1b146"

DEPENDS += "python3-setuptools-scm-native"
