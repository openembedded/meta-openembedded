SUMMARY = "passive checker of Python programs"
HOMEPAGE = "https://github.com/dreamhost/cliff"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=690c2d09203dc9e07c4083fc45ea981f"

SRC_URI[sha256sum] = "e59fd8e750e588358f1b8885e5a4751203a0516e0ee6d34811089ac294c8806f"

inherit pypi setuptools3

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-prettytable \
    ${PYTHON_PN}-cmd2 \
    ${PYTHON_PN}-pyparsing"

BBCLASSEXTEND = "native nativesdk"
