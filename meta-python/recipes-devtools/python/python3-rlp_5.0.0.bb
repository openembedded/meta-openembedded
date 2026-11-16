SUMMARY = "A Python implementation of Recursive Length Prefix encoding (RLP)."
HOMEPAGE = "https://github.com/ethereum/pyrlp"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=00854fa80a84236706b11f47f23e61e7"

SRC_URI[sha256sum] = "ae8ac791160c160e270f9c7df76e68f4d42bb86a13726d807b9357c312d0bac4"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-setuptools-scm-native python3-wheel-native"

RDEPENDS:${PN} += " \
    python3-eth-utils \
    python3-typing-extensions \
"
