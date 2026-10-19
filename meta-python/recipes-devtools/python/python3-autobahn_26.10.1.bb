DESCRIPTION = "WebSocket client & server library, WAMP real-time framework"
HOMEPAGE = "http://crossbar.io/autobahn"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=49165a577911c4178e915dc26e2802a3"

SRC_URI += "file://0001-Skip-march-guessing-for-external-cross-toolchains.patch"

SRC_URI[sha256sum] = "0acc00895d4d3b06b55e4c945592d426033062c989ca2910749777a855faa27a"

CVE_PRODUCT = "autobahn"

inherit pypi python_hatchling

DEPENDS += " \
    python3-cffi-native \
"

RDEPENDS:${PN} += " \
    python3-twisted \
    python3-zopeinterface \
    python3-py-ubjson \
    python3-cbor2 \
    python3-u-msgpack-python \
    python3-lz4 \
    python3-snappy \
    python3-pyopenssl \
    python3-txaio \
"
