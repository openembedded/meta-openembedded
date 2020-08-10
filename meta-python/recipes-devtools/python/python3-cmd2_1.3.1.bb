SUMMARY = "Extra features for standard library's cmd module"
HOMEPAGE = "https://github.com/python-cmd2/cmd2"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9791cd24ca7d1807388ccd55cd066def"

DEPENDS += "${PYTHON_PN}-setuptools-scm-native"

SRC_URI[md5sum] = "9f0af0810da98a30ac92f1e4e7a8c8ab"
SRC_URI[sha256sum] = "c86f3b8348798732759c9d53cbcb6258a8f21f60c1a1268e942d24c9dc50b4c6"

inherit pypi setuptools3

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-attrs \
    ${PYTHON_PN}-colorama \
    ${PYTHON_PN}-pyperclip \
    ${PYTHON_PN}-wcwidth \
"

BBCLASSEXTEND = "native nativesdk"
