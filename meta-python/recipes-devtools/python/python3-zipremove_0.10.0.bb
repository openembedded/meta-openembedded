SUMMARY = "Extend zipfile with remove-related capabilities"
HOMEPAGE = "https://github.com/danny0838/zipremove"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d2da6a8d85b7f2d0302ded46d6865430"

SRC_URI[sha256sum] = "3c5957c115447b54299d3a23128764b59c4558fc98c4b878ff2dbc5db8a62021"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-wheel-native"

RDEPENDS:${PN} += " \
    python3-compression \
    python3-core \
    python3-io \
"

BBCLASSEXTEND = "native nativesdk"
