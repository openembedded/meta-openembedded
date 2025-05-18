SUMMARY = "A modern Python package and dependency manager supporting the latest PEP standards"
HOMEPAGE = "https://pdm-project.org/latest/"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2eb31a2cc1a758c34b499f287dd04ef2"

SRC_URI[sha256sum] = "c08e87078c29c00e7ad9681dc6a23419dc5617dcbe6cc205024ef4b4e7e28475"

inherit pypi python_setuptools_build_meta

DEPENDS += " \
    python3-pdm-backend-native \
"

BBCLASSEXTEND = "native nativesdk"
