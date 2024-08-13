SUMMARY = "Async http client/server framework"
DESCRIPTION = "Asynchronous HTTP client/server framework for asyncio and Python"
HOMEPAGE = "https://github.com/aio-libs/aiohttp"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=748073912af33aa59430d3702aa32d41"

SRC_URI[sha256sum] = "21650e7032cc2d31fc23d353d7123e771354f2a3d5b05a5647fc30fea214e696"

PYPI_PACKAGE = "aiohttp"
inherit python_setuptools_build_meta pypi

RDEPENDS:${PN} = "\
    python3-aiohappyeyeballs \
    python3-aiosignal \
    python3-async-timeout \
    python3-attrs \
    python3-frozenlist \
    python3-misc \
    python3-multidict \
    python3-yarl \
    python3-aiodns \
"
