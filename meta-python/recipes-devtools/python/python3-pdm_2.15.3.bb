SUMMARY = "A modern Python package and dependency manager supporting the latest PEP standards"
HOMEPAGE = "https://pdm-project.org/latest/"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2eb31a2cc1a758c34b499f287dd04ef2"

SRC_URI[sha256sum] = "c227d81f6bf109626a5643a7bb531c5f5b777a850c4eac8d08b472c1146beee5"

inherit pypi python_setuptools_build_meta

DEPENDS += " \
    python3-pdm-backend-native \
"

BBCLASSEXTEND = "native nativesdk"
