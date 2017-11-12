SUMMARY = "Simple, fast, extensible JSON encoder/decoder for Python"
HOMEPAGE = "http://cheeseshop.python.org/pypi/simplejson"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=c6338d7abd321c0b50a2a547e441c52e"

SRC_URI[md5sum] = "048548acae58984b3e939b3bcf182c8e"
SRC_URI[sha256sum] = "df5e38f5e0a24abe0e02276aa5c3f8504150047a51c0b6b848b8153e6e6d395e"

inherit pypi

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-re \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-netserver \
    ${PYTHON_PN}-numbers \
"
