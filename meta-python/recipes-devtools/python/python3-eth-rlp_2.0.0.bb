SUMMARY = "RLP definitions for common Ethereum objects in Python"
HOMEPAGE = "https://github.com/ethereum/eth-rlp"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3d7bdfe69b1ffbde073ca6e96f5c53f7"

SRC_URI[sha256sum] = "70116ec32abd2569fc4ba5f2af80080d896b0dc53a86d582fe1ffa66ecfee308"

inherit pypi setuptools3

RDEPENDS:${PN} = " \
    python3-eth-utils \
    python3-hexbytes \
    python3-rlp \
    python3-typing-extensions \
"
