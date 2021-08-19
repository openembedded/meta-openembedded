SUMMARY = "YAML parser/emitter that supports roundtrip preservation of comments, seq/map flow style, and map key order."
AUTHOR = "Anthon van der Neut"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fa0a51dfb461e2f803969e0f3fa71dfe"

PYPI_PACKAGE = "ruamel.yaml"

inherit pypi setuptools3

SRC_URI[sha256sum] = "b5e96c0a6619830a7733099f0599b56556a963c6114ef64d0ddb4673347b426f"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-shell \
"

do_install:prepend() {
    export RUAMEL_NO_PIP_INSTALL_CHECK=1
}

BBCLASSEXTEND = "native nativesdk"
