SUMMARY = "A tool for creating isolated virtual python environments."
DESCRIPTION = "Virtual Python Environment builder"
HOMEPAGE = "https://github.com/pypa/virtualenv"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=36ef9437b761b314fa717c786c5ca460"

SRC_URI[sha256sum] = "c8b65c7d206ca6ef18de9352c160b3f04f66fff9f6b0ec8fd31f14688eba52c4"

BBCLASSEXTEND = "native nativesdk"
inherit pypi python_hatchling

DEPENDS += "\
    python3-hatch-fancy-pypi-readme-native \
    python3-hatch-vcs-native \
"

RDEPENDS:${PN} += " \
    python3-compression  \
    python3-distlib \
    python3-filelock \
    python3-json \
    python3-misc \
    python3-modules \
    python3-platformdirs \
"

CVE_PRODUCT = "virtualenv"
