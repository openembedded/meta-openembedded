SUMMARY = "A modern Python package and dependency manager supporting the latest PEP standards"
HOMEPAGE = "https://pdm-project.org/latest/"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2eb31a2cc1a758c34b499f287dd04ef2"

SRC_URI[sha256sum] = "9c928d6db62d104ab86318fe09aaf9bdfc6f616176af407e4df00f59e001930f"

inherit pypi python_setuptools_build_meta

DEPENDS += " \
    python3-pdm-backend-native \
    python3-pdm-build-locked-native \
"

BBCLASSEXTEND = "native nativesdk"
