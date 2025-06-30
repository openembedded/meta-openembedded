SUMMARY = "Extra features for standard library's cmd module"
HOMEPAGE = "https://github.com/python-cmd2/cmd2"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=26efe44f9946f43292f90070d9d5590c"

DEPENDS += "python3-setuptools-scm-native"

SRC_URI[sha256sum] = "bde128c0b6dba9d2d9179a1c7c8fe92ab930473d0ddf46f2bc19cb789f942e4d"

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
