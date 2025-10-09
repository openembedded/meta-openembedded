SUMMARY = "Telnet server and client library based on asyncio"
HOMEPAGE = "https://github.com/jquast/telnetlib3"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=b2cbfe1ec99d8830fa20d62c8f21d0e8"

SRC_URI[sha256sum] = "08a2a7a3a6790f89617442b7d491da8b531d87706fffd6a33eeff57ac440b752"

PYPI_PACKAGE = "telnetlib3"

inherit pypi setuptools3

RDEPENDS:${PN} = "\
    python3-asyncio \
"
