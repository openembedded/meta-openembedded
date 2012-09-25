DESCRIPTION = "BSD replacement for libreadline"
HOMEPAGE = "http://www.thrysoee.dk/editline/"
SECTION = "libs"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=1e4228d0c5a9093b01aeaaeae6641533"

DEPENDS = "ncurses"

inherit autotools

SRC_URI = "http://www.thrysoee.dk/editline/${PN}-${PV}-3.0.tar.gz"

S = "${WORKDIR}/${PN}-${PV}-3.0"

SRC_URI[md5sum] = "e50f6a7afb4de00c81650f7b1a0f5aea"
SRC_URI[sha256sum] = "51f0f4b4a97b7ebab26e7b5c2564c47628cdb3042fd8ba8d0605c719d2541918"
