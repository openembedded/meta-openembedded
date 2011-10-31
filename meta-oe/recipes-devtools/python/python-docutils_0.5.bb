DESCRIPTION = "Text processing system"
HOMEPAGE = "http://docutils.sourceforge.net"
SECTION = "devel/python"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=ac6ee29ac0310c091afab5ac4bea2fa3"
PR = "r1"

DEPENDS = "python"

SRC_URI = "${SOURCEFORGE_MIRROR}/docutils/docutils-${PV}.tar.gz"
SRC_URI[md5sum] = "dd72dac92fc8e3eb0f48c3effeef80f6"
SRC_URI[sha256sum] = "747cf984edfca0575addbb42453274a1bdd98ec7780bd37a883dc8b2a66a610e"

S = "${WORKDIR}/docutils-${PV}"

inherit distutils

NATIVE_INSTALL_WORKS = "1"
BBCLASSEXTEND = "native"

