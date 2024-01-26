SUMMARY = "A modern Python package and dependency manager supporting the latest PEP standards"
HOMEPAGE = "https://pdm-project.org/latest/"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2eb31a2cc1a758c34b499f287dd04ef2"

SRC_URI[sha256sum] = "b3ca4ab107ad65b57fe1312e4360e1f7b3d7c5ed81a841b6ed48b3777862ed57"

inherit pypi python_setuptools_build_meta

DEPENDS += " \
    ${PYTHON_PN}-pdm-backend-native \
"

BBCLASSEXTEND = "native nativesdk"
