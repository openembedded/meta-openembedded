SUMMARY = "An implementation of the WebSocket Protocol (RFC 6455)"
HOMEPAGE = "https://github.com/aaugustin/websockets"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=78c2cc91e172ca96d6f8e4a76c739ec6"

inherit pypi setuptools3

SRC_URI[sha256sum] = "6550ecf06e39b7b009605d332dc58b04f3f6f8fe087d452bb2ea4712fbbdcbe6"

BBCLASSEXTEND = "native nativesdk"

RDEPENDS_${PN} = "\
    ${PYTHON_PN}-asyncio \
"
