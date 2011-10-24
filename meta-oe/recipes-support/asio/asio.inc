DESCRIPTION = "Asio"
HOMEPAGE = "http://asio.sf.net/"
SECTION = "libs"
LICENSE = "Boost Software License"

INC_PR = "r0"

DEPENDS = "boost"

SRC_URI = "${SOURCEFORGE_MIRROR}/asio/${PN}-${PV}.tar.bz2"

inherit autotools

