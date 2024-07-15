SUMMARY = "The build backend used by PDM that supports latest packaging standards"
HOMEPAGE = "https://github.com/pdm-project/pdm-backend"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4a564297b3c5b629a528b92fd8ff61ea"

SRC_URI[sha256sum] = "b0c47e6a5f6bd7b0e2263eede0e6b2cf164a83ade81b7e73dc10f8254f60b828"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE= "pdm_backend"

BBCLASSEXTEND = "native nativesdk"
