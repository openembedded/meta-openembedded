SUMMARY = "Async http client/server framework"
DESCRIPTION = "Asynchronous HTTP client/server framework for asyncio and Python"
HOMEPAGE = "https://github.com/aio-libs/aiohttp"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=cf056e8e7a0a5477451af18b7b5aa98c"

SRC_URI[md5sum] = "13d02dc8379207dde1f6966d1fc5083d"
SRC_URI[sha256sum] = "698cd7bc3c7d1b82bb728bae835724a486a8c376647aec336aa21a60113c3645"

PYPI_PACKAGE = "aiohttp"
inherit setuptools3 pypi
RDEPENDS_${PN} = "\
    ${PYTHON_PN}-async-timeout \
    ${PYTHON_PN}-attrs \
    ${PYTHON_PN}-chardet \
    ${PYTHON_PN}-idna-ssl \
    ${PYTHON_PN}-misc \
    ${PYTHON_PN}-multidict \
    ${PYTHON_PN}-typing \
    ${PYTHON_PN}-yarl \
"
