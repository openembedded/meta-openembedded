SUMMARY = "Python lightweight in-process concurrent programming"
HOMEPAGE = "http://pypi.python.org/pypi/greenlet"
SECTION = "devel/python"
LICENSE = "MIT & PSF"
LIC_FILES_CHKSUM = "file://LICENSE;md5=03143d7a1a9f5d8a0fee825f24ca9c36 \
                    file://LICENSE.PSF;md5=c106931d9429eda0492617f037b8f69a"
SRC_URI = "http://pypi.python.org/packages/source/g/greenlet/greenlet-${PV}.zip"
SRC_URI[md5sum] = "530a69acebbb0d66eb5abd83523d8272"
SRC_URI[sha256sum] = "259ed0f34de9b0c948f42bdaffe378ea53d210d38377c6dcb0c6b728fccad1b0"

S = "${WORKDIR}/greenlet-${PV}"

inherit distutils

