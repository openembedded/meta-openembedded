SUMMARY = "The build backend used by PDM that supports latest packaging standards"
HOMEPAGE = "https://github.com/pdm-project/pdm-backend"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4a564297b3c5b629a528b92fd8ff61ea"

SRC_URI[sha256sum] = "a8616f628ec84353d7a0ba86b228dcf01bab5debc9e4d1a29e5311a52425d594"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE= "pdm_backend"

BBCLASSEXTEND = "native nativesdk"
