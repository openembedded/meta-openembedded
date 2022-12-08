SUMMARY = "A Python library for interacting with Ethereum."
HOMEPAGE = "https://github.com/ethereum/web3.py"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1dc2732bdc5e50382737979791cbb3b7"

SRC_URI[sha256sum] = "fc27ef2acacb9375e1d486ce7b0f4188c5e543d65f7bb8692f8b969f408bfa9b"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    python3-aiohttp \
    python3-distutils \
    python3-eth-account \
    python3-idna \
    python3-lru-dict \
    python3-requests \
    python3-setuptools \
    python3-websockets \
"
