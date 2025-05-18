SUMMARY = "Telnet server and client library based on asyncio"
HOMEPAGE = "https://github.com/jquast/telnetlib3"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=fc2166986ad8169d334a342e0d8db8e0"

SRC_URI[md5sum] = "2dfac7e10ed63c408da50ea712415f87"
SRC_URI[sha256sum] = "dbcbc16456a0e03a62431be7cfefff00515ab2f4ce2afbaf0d3a0e51a98c948d"

PYPI_PACKAGE = "telnetlib3"

inherit pypi setuptools3

RDEPENDS:${PN} = "\
    ${PYTHON_PN}-asyncio \
"
