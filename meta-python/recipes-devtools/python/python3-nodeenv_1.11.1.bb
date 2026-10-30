SUMMARY = "Node.js virtual environment builder"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a9e238ffae5bb6447dbac6291e1dc3a3"


inherit pypi python_setuptools_build_meta
SRC_URI[sha256sum] = "d7fa027c1df437bdd974b0502a8dc76fa1b3183e369ac9539602bc6bab9100ba"

DEPENDS += "python3-setuptools-scm-native"
