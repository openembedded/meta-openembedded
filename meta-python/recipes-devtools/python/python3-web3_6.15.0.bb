SUMMARY = "A Python library for interacting with Ethereum."
HOMEPAGE = "https://github.com/ethereum/web3.py"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=373fede350846fdffd23648fba504635"

SRC_URI[sha256sum] = "a646d1fe5eba00625b5459d23910e472f0d0aa063788422bff2648ca4e1ebc7e"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    python3-aiohttp \
    python3-eth-account \
    python3-idna \
    python3-jsonschema \
    python3-google-api-core \
    python3-lru-dict \
    python3-requests \
    python3-setuptools \
    python3-websockets \
"
