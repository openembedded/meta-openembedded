DESCRIPTION = "Text processing system"
HOMEPAGE = "http://docutils.sourceforge.net"
SECTION = "devel/python"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=21eae6cbbd42b24a4243269f39e5d592"

DEPENDS = "python"

SRC_URI = "${SOURCEFORGE_MIRROR}/docutils/docutils-${PV}.tar.gz"
SRC_URI[md5sum] = "b0d5cd5298fedf9c62f5fd364a274d56"
SRC_URI[sha256sum] = "e89f187dbbc6674f839239c89fec44af9f18809b66a8a55a41b57b9ee2356994"

S = "${WORKDIR}/docutils-${PV}"

inherit distutils

BBCLASSEXTEND = "native"

