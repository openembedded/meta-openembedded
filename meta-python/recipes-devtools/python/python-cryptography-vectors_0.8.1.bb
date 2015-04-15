SUMMARY = "Test vectors for the cryptography package."
SECTION = "devel/python"
LICENSE = "Apache-2.0 | BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8c3617db4fb6fae01f1d253ab91511e4"
DEPENDS = "python-cryptography"
SRCNAME = "cryptography_vectors"

SRC_URI = "https://pypi.python.org/packages/source/c/cryptography-vectors/${SRCNAME}-${PV}.tar.gz"
S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI[md5sum] = "04fcfcc61aee62e3b4beb1740cd120ac"
SRC_URI[sha256sum] = "4a69e66026bfce4b97f75e72fe262f5e8f71daddc081f6608e5b0db89e552504"

inherit setuptools
