SUMMARY = "A tool for creating isolated virtual python environments."
DESCRIPTION = "Virtual Python Environment builder"
HOMEPAGE = "https://github.com/pypa/virtualenv"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=36ef9437b761b314fa717c786c5ca460"

SRC_URI[sha256sum] = "f3ad23551343034e613f9c4d8279f282958a19729f5fc2bcb758afcf95fe3a01"

BBCLASSEXTEND = "native nativesdk"
inherit pypi python_hatchling

DEPENDS += "\
    python3-hatch-fancy-pypi-readme-native \
    python3-hatch-vcs-native \
    python3-python-sbom-native \
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
