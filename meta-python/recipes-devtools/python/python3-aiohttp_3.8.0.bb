SUMMARY = "Async http client/server framework"
DESCRIPTION = "Asynchronous HTTP client/server framework for asyncio and Python"
HOMEPAGE = "https://github.com/aio-libs/aiohttp"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=8074d6c6e217873b2a018a4522243ea3"

SRC_URI[sha256sum] = "d3b19d8d183bcfd68b25beebab8dc3308282fe2ca3d6ea3cb4cd101b3c279f8d"

PYPI_PACKAGE = "aiohttp"
inherit setuptools3 pypi
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
"
