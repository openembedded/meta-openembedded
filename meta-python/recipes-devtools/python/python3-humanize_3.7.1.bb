SUMMARY = "Python humanize utilities"
HOMEPAGE = "http://github.com/jmoiron/humanize"
SECTION = "devel/python"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENCE;md5=4ecc42519e84f6f3e23529464df7bd1d"

SRC_URI[sha256sum] = "b8e7878f3063174b212bb82b9e5bee3b24bc47931e44df0bd34bcb1d8e0acf2f"

inherit pypi setuptools3

DEPENDS += "\
    ${PYTHON_PN}-setuptools-scm-native \
"

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-setuptools \
"

BBCLASSEXTEND = "native nativesdk"
