SUMMARY = "A friendly Python library for async concurrency and I/O"
HOMEPAGE = "https://github.com/python-trio/trio"
LICENSE = "Apache-2.0 & MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=447ea202d14d2aee40d8a2c26c865da9 \
                    file://LICENSE.APACHE2;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://LICENSE.MIT;md5=5f229c828e5a6f0a2ce90c7d3c054721"

SRC_URI[sha256sum] = "0346c3852c15e5c7d40ea15972c4805689ef2cb8b5206f794c9c19450119f3a4"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE = "trio"
RDEPENDS:${PN} = "python3-idna python3-sniffio"
