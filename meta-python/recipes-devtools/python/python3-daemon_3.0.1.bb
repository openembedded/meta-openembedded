DESCRIPTION = "Library to implement a well-behaved Unix daemon process"
HOMEPAGE = "https://pagure.io/python-daemon/"
SECTION = "devel/python"

DEPENDS += "${PYTHON_PN}-docutils-native"
RDEPENDS:${PN} = "${PYTHON_PN}-docutils \
                  ${PYTHON_PN}-lockfile (>= 0.10) \
                  ${PYTHON_PN}-resource \
"

LICENSE = "Apache-2.0 & GPL-3.0-only"
LIC_FILES_CHKSUM = "file://README;md5=a3a94c615dc969a70525f1eebbacf235"

inherit pypi setuptools3

SRC_URI[sha256sum] = "6c57452372f7eaff40934a1c03ad1826bf5e793558e87fef49131e6464b4dae5"

PYPI_PACKAGE = "python-daemon"
