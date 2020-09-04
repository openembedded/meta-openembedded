SUMMARY = "YAML parser/emitter that supports roundtrip preservation of comments, seq/map flow style, and map key order."
AUTHOR = "Anthon van der Neut"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=60afc0a1bb0501c0c555cabe78bba022"

PYPI_PACKAGE = "ruamel.yaml"

inherit pypi setuptools3

SRC_URI[md5sum] = "020a0140d06b2e32f53944d4335f9877"
SRC_URI[sha256sum] = "43bf19037937f78845775d84582dd6f3ac966bc1b7748de898fad35638c74771"

do_install_prepend() {
    export RUAMEL_NO_PIP_INSTALL_CHECK=1
}
