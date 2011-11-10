DESCRIPTION = "Control basic functions in socketcan from userspace"
HOMEPAGE = "http://www.pengutronix.de"
SECTION = "libs/network"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://src/libsocketcan.c;startline=3;endline=17;md5=2c5c54d58b42d2216f34759062be3ddb"

SRCREV = "3a2cec63a4e6212cc63d21008453bd799cfe8774"

SRC_URI = "git://git.pengutronix.de/git/tools/libsocketcan.git;protocol=git \
	"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_configure_prepend() {
	sed -i -e s:tests/GNUmakefile::g -e s:trunk:0.0.8: configure.ac
}
