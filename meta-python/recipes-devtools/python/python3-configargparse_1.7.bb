SUMMARY = "A drop-in replacement for argparse that allows options to also be set via config files and/or environment variables."
HOMEPAGE = "https://github.com/bw2/ConfigArgParse"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=da746463714cc35999ed9a42339f2943"

SRC_URI[sha256sum] = "e7067471884de5478c58a511e529f0f9bd1c66bfef1dea90935438d6c23306d1"

PYPI_PACKAGE = "ConfigArgParse"

inherit pypi setuptools3

PACKAGECONFIG ?= "yaml"
PACKAGECONFIG[yaml] = ",,,${PYTHON_PN}-pyyaml"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-core \
    ${PYTHON_PN}-shell \
    ${PYTHON_PN}-json \
"

BBCLASSEXTEND = "native nativesdk"
