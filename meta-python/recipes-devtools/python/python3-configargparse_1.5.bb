SUMMARY = "A drop-in replacement for argparse that allows options to also be set via config files and/or environment variables."
HOMEPAGE = "https://github.com/bw2/ConfigArgParse"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=da746463714cc35999ed9a42339f2943"

SRC_URI[sha256sum] = "dded3590373b7dae6ce6d0afeb4ae3def74761fdd78730952863914d4cb4bdb5"

PYPI_PACKAGE = "ConfigArgParse"

inherit pypi setuptools3

PACKAGECONFIG ?= "yaml"
PACKAGECONFIG[yaml] = ",,,${PYTHON_PN}-pyyaml"

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-core \
    ${PYTHON_PN}-shell \
    ${PYTHON_PN}-json \
"

BBCLASSEXTEND = "native nativesdk"
