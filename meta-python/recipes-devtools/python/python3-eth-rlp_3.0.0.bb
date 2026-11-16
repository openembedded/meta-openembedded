SUMMARY = "RLP definitions for common Ethereum objects in Python"
HOMEPAGE = "https://github.com/ethereum/eth-rlp"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3ef06eb4d4373222e59a338e2eb9a795"

SRC_URI[sha256sum] = "9663e54a4a1c1c847d2d328c1d07e4174ec1c082953fbb42b60e61c501c4931c"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-setuptools-scm-native python3-wheel-native"


RDEPENDS:${PN} = " \
    python3-eth-utils \
    python3-hexbytes \
    python3-rlp \
    python3-typing-extensions \
"
