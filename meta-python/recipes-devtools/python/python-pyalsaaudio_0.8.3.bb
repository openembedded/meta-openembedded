SUMMARY = "Support for the Linux 2.6.x ALSA Sound System"
SECTION = "devel/python"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1a3b161aa0fcec32a0c8907a2219ad9d"

SRC_URI[md5sum] = "fd87fdd34fb0aaf7e48bfe036ec8e2f7"
SRC_URI[sha256sum] = "74d9f04e0aeb71b0b5d36c0f02b0dd69b227ae7324cb5ae70d1e22a61459573e"

DEPENDS += "alsa-lib"

inherit pypi setuptools

RDEPENDS_${PN} += "alsa-lib"
