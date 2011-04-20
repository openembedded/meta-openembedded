DESCRIPTION = "A packet dissection and creation library"
# libnet at packetfactory.net is dead
HOMEPAGE = "https://github.com/sam-github/libnet"
SECTION = "libs"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://README;md5=533bea47338d490e86744c6a0c5692e7"
DEPENDS = "libpcap"
# There are major API changes beween libnet v1.0 and libnet v1.1
PROVIDES = "libnet-1.1"
PR = "r0"

SRC_URI = "${SOURCEFORGE_MIRROR}/libnet-dev/${PN}-${PV}.tar.gz \
           file://fix-endianess-test.patch"

SRC_URI[md5sum] = "a9bc1d75a610efcfee200d3e28d8eb8f"
SRC_URI[sha256sum] = "75588f8d1232e1df34eccf2dba5e66be197b345a07fae80d8275db994496a20d"

S = "${WORKDIR}/${PN}-${PV}"

inherit autotools binconfig

do_configure_prepend() {
        rm -f aclocal.m4 Makefile.am ltmain.sh
}
