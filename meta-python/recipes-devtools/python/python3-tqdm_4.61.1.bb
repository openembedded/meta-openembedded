SUMMARY = "Fast, Extensible Progress Meter"
HOMEPAGE = "http://tqdm.github.io/"
SECTION = "devel/python"

LICENSE = "MIT & MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENCE;md5=59e4271a933d33edfe60237db377a14b"

SRC_URI[sha256sum] = "24be966933e942be5f074c29755a95b315c69a91f839a29139bf26ffffe2d3fd"

inherit pypi setuptools3

DEPENDS += "${PYTHON_PN}-setuptools-scm-native"

BBCLASSEXTEND = "native nativesdk"
