SUMMARY = "A module wrapper for os.path"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7a7126e068206290f3fe9f8d6c713ea6"

SRC_URI[sha256sum] = "baf2e757c4b19be8208f9e67e48fb475b4a577d5613590ce46693bdbdf082f52"

inherit pypi setuptools_build_meta
PYPA_WHEEL = "${PIP_INSTALL_DIST_PATH}/${PIP_INSTALL_PACKAGE}-0.0.0-*.whl"

BBCLASSEXTEND = "nativesdk native"
