SUMMARY = "Async http client/server framework"
DESCRIPTION = "Asynchronous HTTP client/server framework for asyncio and Python"
HOMEPAGE = "https://github.com/aio-libs/aiohttp"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=748073912af33aa59430d3702aa32d41"

SRC_URI[sha256sum] = "b0ad0a5e86ce73f5368a164c10ada10504bf91869c05ab75d982c6048217fbf7"

PYPI_PACKAGE = "aiohttp"
inherit python_setuptools_build_meta pypi

RDEPENDS:${PN} = "\
    ${PYTHON_PN}-aiohappyeyeballs \
    ${PYTHON_PN}-aiosignal \
    ${PYTHON_PN}-async-timeout \
    ${PYTHON_PN}-frozenlist \
    ${PYTHON_PN}-multidict \
    ${PYTHON_PN}-yarl \
    ${PYTHON_PN}-aiodns \
"
