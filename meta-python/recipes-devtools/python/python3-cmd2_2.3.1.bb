SUMMARY = "Extra features for standard library's cmd module"
HOMEPAGE = "https://github.com/python-cmd2/cmd2"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4c527bcb481233ebcb803de975f42701"

DEPENDS += "${PYTHON_PN}-setuptools-scm-native"

SRC_URI[sha256sum] = "44f9d93bbcd90c6dda70e73db722d6b27b551d98bef28bd82fd14ccbe88d18ea"

inherit pypi setuptools3

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-attrs \
    ${PYTHON_PN}-colorama \
    ${PYTHON_PN}-pyperclip \
    ${PYTHON_PN}-wcwidth \
    ${PYTHON_PN}-compression \
    ${PYTHON_PN}-pydoc \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-numbers \
"

BBCLASSEXTEND = "native nativesdk"
