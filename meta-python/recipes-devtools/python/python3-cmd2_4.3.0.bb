SUMMARY = "Extra features for standard library's cmd module"
HOMEPAGE = "https://github.com/python-cmd2/cmd2"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f2a861642858e0858af9dd4e4716bae0"

DEPENDS += "python3-setuptools-scm-native"

SRC_URI[sha256sum] = "3f6c4b9619dbd0d1ba75789a099be82b65daf8ac1d97b8e4ecb112e76c72b100"

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
    python3-rich-argparse \
    python3-prompt-toolkit \
"

BBCLASSEXTEND = "native nativesdk"
