SUMMARY = "A Python library for interacting with Ethereum."
HOMEPAGE = "https://github.com/ethereum/web3.py"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=373fede350846fdffd23648fba504635"

SRC_URI[sha256sum] = "77340b9a8109835a680950e09f21ae77e41ea38076215ab8be2ba4bee7b8034a"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    python3-aiohttp \
    python3-distutils \
    python3-eth-account \
    python3-idna \
    python3-jsonschema \
    python3-google-api-core \
    python3-lru-dict \
    python3-requests \
    python3-setuptools \
    python3-websockets \
"
