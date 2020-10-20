DESCRIPTION = "Backport of pathlib-compatible object wrapper for zip files"
HOMEPAGE = "https://github.com/jaraco/zipp"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7a7126e068206290f3fe9f8d6c713ea6"

SRC_URI[md5sum] = "8bc697a6f65b8e2f75bcadfb90e98e58"
SRC_URI[sha256sum] = "c1532a8030c32fd52ff6a288d855fe7adef5823ba1d26a29a68fd6314aa72baa"

DEPENDS += "${PYTHON_PN}-setuptools-scm-native"

inherit pypi setuptools3

DEPENDS += "${PYTHON_PN}-toml-native"

RDEPENDS_${PN} += "${PYTHON_PN}-compression \
                   ${PYTHON_PN}-math \
                   ${PYTHON_PN}-more-itertools"

BBCLASSEXTEND = "native nativesdk"
