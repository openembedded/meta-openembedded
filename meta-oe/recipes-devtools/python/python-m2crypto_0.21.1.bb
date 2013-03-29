DESCRIPTION = "A Python crypto and SSL toolkit"
HOMEPAGE = "http://chandlerproject.org/bin/view/Projects/MeTooCrypto"

DEPENDS = "openssl swig-native"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENCE;md5=b0e1f0b7d0ce8a62c18b1287b991800e"

SRC_URI = "http://pypi.python.org/packages/source/M/M2Crypto/M2Crypto-${PV}.tar.gz \
           file://0001-setup.py-link-in-sysroot-not-in-host-directories.patch"

SRC_URI[md5sum] = "f93d8462ff7646397a9f77a2fe602d17"
SRC_URI[sha256sum] = "25b94498505c2d800ee465db0cc1aff097b1615adc3ac042a1c85ceca264fc0a"

S = "${WORKDIR}/M2Crypto-${PV}"

inherit setuptools

BBCLASSEXTEND = "native"
