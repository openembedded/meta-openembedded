SUMMARY = "Async http client/server framework"
DESCRIPTION = "Asynchronous HTTP client/server framework for asyncio and Python"
HOMEPAGE = "https://github.com/aio-libs/aiohttp"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=748073912af33aa59430d3702aa32d41"

SRC_URI[sha256sum] = "edea7d15772ceeb29db4aff55e482d4bcfb6ae160ce144f2682de02f6d693551"

SRC_URI += "file://CVE-2024-52304.patch \
           file://CVE-2025-53643.patch \
           file://CVE-2025-69224.patch \
           file://CVE-2025-69225.patch \
           file://CVE-2025-69226.patch \
           file://CVE-2025-69228.patch \
           file://CVE-2025-69229_p1.patch \
           file://CVE-2025-69229_p2.patch \
           file://CVE-2025-69227.patch \
           file://CVE-2025-69223.patch \
           "

CVE_STATUS[CVE-2026-34515] = "not-applicable-platform: Vulnerability only affects applications running on Windows"

PYPI_PACKAGE = "aiohttp"
inherit python_setuptools_build_meta pypi

DEPENDS += "python3-cython-native"

do_configure:prepend() {
    cython3 -3 -Werror \
        -I ${S}/aiohttp \
        -o ${S}/aiohttp/_http_parser.c \
        ${S}/aiohttp/_http_parser.pyx
}

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
