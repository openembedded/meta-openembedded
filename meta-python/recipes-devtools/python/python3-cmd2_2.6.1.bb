SUMMARY = "Extra features for standard library's cmd module"
HOMEPAGE = "https://github.com/python-cmd2/cmd2"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=01d2b71040983a2abc614fc4d0284771"

DEPENDS += "python3-setuptools-scm-native"

SRC_URI[sha256sum] = "650a5892bf29b233d3d6775b5e3cc813648cff0d79134f707981f66baaed9f42"

inherit pypi python_setuptools_build_meta python3native

RDEPENDS:${PN} += "\
    python3-attrs \
    python3-colorama \
    python3-pyperclip \
    python3-wcwidth \
    python3-compression \
    python3-pydoc \
    python3-json \
    python3-numbers \
"

BBCLASSEXTEND = "native nativesdk"
