SUMMARY = "Extra features for standard library's cmd module"
HOMEPAGE = "https://github.com/python-cmd2/cmd2"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9791cd24ca7d1807388ccd55cd066def"

DEPENDS += "${PYTHON_PN}-setuptools-scm-native"

SRC_URI[md5sum] = "255ba85d8aaa9f00d84917be38b11a86"
SRC_URI[sha256sum] = "b6f6254def8ba479088702f97bca1b999c12e0c38ac5d82dc50a44db93c7108c"

inherit pypi setuptools3

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-attrs \
    ${PYTHON_PN}-colorama \
    ${PYTHON_PN}-pyperclip \
    ${PYTHON_PN}-wcwidth \
"

BBCLASSEXTEND = "native nativesdk"
