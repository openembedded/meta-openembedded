SUMMARY = "A modern Python package and dependency manager supporting the latest PEP standards"
HOMEPAGE = "https://pdm-project.org/latest/"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2eb31a2cc1a758c34b499f287dd04ef2"

SRC_URI[sha256sum] = "d04877362f95cf9ffc1d2c38b851f693706e4928840e48986ae576dad5741496"

inherit pypi python_setuptools_build_meta

DEPENDS += " \
    python3-pdm-backend-native \
"

BBCLASSEXTEND = "native nativesdk"
