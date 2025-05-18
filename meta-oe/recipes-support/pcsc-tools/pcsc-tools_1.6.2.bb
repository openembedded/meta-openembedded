SUMMARY = "Some tools to be used with smart cards and PC/SC"
HOMEPAGE = "http://ludovic.rousseau.free.fr/softwares/pcsc-tools"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENCE;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "git://github.com/LudovicRousseau/pcsc-tools;protocol=https;branch=master"

SRCREV = "b1018b83346f8a3bc3d42c96f2ed4dc62147cb24"

inherit autotools pkgconfig

S = "${WORKDIR}/git"

DEPENDS = "pcsc-lite"

FILES:${PN} += "${datadir}/pcsc/smartcard_list.txt"
