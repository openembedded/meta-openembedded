SUMMARY = "Telnet server and client library based on asyncio"
HOMEPAGE = "https://github.com/jquast/telnetlib3"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=15abe157ad6f0b483975cc34bcc1aa99"

SRC_URI[sha256sum] = "71c845a3cc339d462cd54e5926b16e5fb38b775febf2fb3ce1137b6bb30e92fa"


inherit pypi python_setuptools_build_meta python_hatchling

RDEPENDS:${PN} = "\
    python3-asyncio \
    python3-wcwidth \
"
