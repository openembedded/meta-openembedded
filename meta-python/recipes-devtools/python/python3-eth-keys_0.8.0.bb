SUMMARY = "A common API for Ethereum key operations with pluggable backends."
HOMEPAGE = "https://github.com/ethereum/eth-keys"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d3f53e7cc5bf80b16eff1f4a38c73182"

SRC_URI[sha256sum] = "11549b251876fccd7caedd6905e494ea2309aec352ec2579b00ef9978017a964"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-setuptools-scm-native python3-wheel-native"


RDEPENDS:${PN} += "python3-eth-utils"
