SUMMARY = "A packet dissection and creation library"
# libnet at packetfactory.net is dead
HOMEPAGE = "https://github.com/sam-github/libnet"
SECTION = "libs"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://doc/COPYING;md5=fb43d5727b2d3d1238545f75ce456ec3"
DEPENDS = "libpcap"
# There are major API changes beween libnet v1.0 and libnet v1.1
PROVIDES = "libnet-1.1"

SRC_URI = "${SOURCEFORGE_MIRROR}/libnet-dev/${BPN}-${PV}.tar.gz \
           file://fix-endianess-test.patch"

SRC_URI[md5sum] = "710296fe424a49344e5fcc0d09e53317"
SRC_URI[sha256sum] = "d392bb5825c4b6b672fc93a0268433c86dc964e1500c279dc6d0711ea6ec467a"

S = "${WORKDIR}/${BPN}-${PV}"

inherit autotools binconfig

do_configure_prepend() {
    rm -f aclocal.m4 Makefile.am ltmain.sh
}
