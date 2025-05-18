SUMMARY = "Capture the outcome of Python function calls."
HOMEPAGE = "https://github.com/python-trio/outcome"
LICENSE = "Apache-2.0 & MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fa7b86389e58dd4087a8d2b833e5fe96 \
                    file://LICENSE.APACHE2;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://LICENSE.MIT;md5=e62ba5042d5983462ad229f5aec1576c"

SRC_URI[sha256sum] = "9dcf02e65f2971b80047b377468e72a268e15c0af3cf1238e6ff14f7f91143b8"

inherit pypi setuptools3

RDEPENDS:${PN} += "python3-attrs"

RDEPENDS:${PN} += "python3-asyncio python3-core python3-pytest python3-typing-extensions"

PYPI_PACKAGE = "outcome"
