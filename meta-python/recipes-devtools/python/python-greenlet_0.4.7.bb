SUMMARY = "Python lightweight in-process concurrent programming"
HOMEPAGE = "http://pypi.python.org/pypi/greenlet"
SECTION = "devel/python"
LICENSE = "MIT & PSF"
LIC_FILES_CHKSUM = "file://LICENSE;md5=03143d7a1a9f5d8a0fee825f24ca9c36 \
                    file://LICENSE.PSF;md5=c106931d9429eda0492617f037b8f69a"
SRC_URI = "http://pypi.python.org/packages/source/g/greenlet/greenlet-${PV}.zip"
SRC_URI[md5sum] = "c2333a8ff30fa75c5d5ec0e67b461086"
SRC_URI[sha256sum] = "f32c4fa4e06443e1bdb0d32b69e7617c25ff772c3ffc6d0aa63d192e9fd795fe"

S = "${WORKDIR}/greenlet-${PV}"

inherit distutils

