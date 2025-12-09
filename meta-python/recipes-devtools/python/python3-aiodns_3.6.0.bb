SUMMARY = "Simple DNS resolver for asyncio"
DESCRIPTION = "aiodns provides a simple way for doing asynchronous DNS resolutions using pycares."
HOMEPAGE = "https://github.com/saghul/aiodns"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d574ab425d1fcb37c9f1ad3961f18527"

SRC_URI[sha256sum] = "9b0ef54339e6687cdbd39a7d73d7de2467cb5c115281da28f2598f058633dac8"

PYPI_PACKAGE = "aiodns"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    python3-asyncio \
    python3-pycares \
"

BBCLASSEXTEND = "native nativesdk"
