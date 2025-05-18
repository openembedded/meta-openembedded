SUMMARY = "Async http client/server framework"
DESCRIPTION = "Asynchronous HTTP client/server framework for asyncio and Python"
HOMEPAGE = "https://github.com/aio-libs/aiohttp"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=748073912af33aa59430d3702aa32d41"

SRC_URI[sha256sum] = "b9552ec52cc147dbf1944ac7ac98af7602e51ea2dcd076ed194ca3c0d1c7d0bc"

PYPI_PACKAGE = "aiohttp"
inherit python_setuptools_build_meta pypi

RDEPENDS:${PN} = "\
    ${PYTHON_PN}-async-timeout \
    ${PYTHON_PN}-attrs \
    ${PYTHON_PN}-chardet \
    ${PYTHON_PN}-html \
    ${PYTHON_PN}-idna-ssl \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-misc \
    ${PYTHON_PN}-multidict \
    ${PYTHON_PN}-netserver \
    ${PYTHON_PN}-typing-extensions \
    ${PYTHON_PN}-yarl \
    ${PYTHON_PN}-cchardet \
    ${PYTHON_PN}-charset-normalizer \
    ${PYTHON_PN}-aiosignal \
"
