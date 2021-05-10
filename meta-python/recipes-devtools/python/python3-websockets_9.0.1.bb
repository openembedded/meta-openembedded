SUMMARY = "An implementation of the WebSocket Protocol (RFC 6455)"
HOMEPAGE = "https://github.com/aaugustin/websockets"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=78c2cc91e172ca96d6f8e4a76c739ec6"

inherit pypi setuptools3

SRC_URI[sha256sum] = "2ab64e9fd18e57a66b63a8774e337d81d6366412bef65c7d71f87ad5c4faeed5"

BBCLASSEXTEND = "native nativesdk"

RDEPENDS_${PN} = "\
    ${PYTHON_PN}-asyncio \
"
