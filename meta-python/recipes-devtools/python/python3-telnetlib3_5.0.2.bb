SUMMARY = "Telnet server and client library based on asyncio"
HOMEPAGE = "https://github.com/jquast/telnetlib3"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=15abe157ad6f0b483975cc34bcc1aa99"

SRC_URI[sha256sum] = "30bee6ccbacc5c7540ae1a4fb990809eb81993d98900597c0795a5d1ad45e40f"


inherit pypi python_setuptools_build_meta python_hatchling

RDEPENDS:${PN} = "\
    python3-asyncio \
    python3-wcwidth \
"
