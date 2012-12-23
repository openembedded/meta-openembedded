DESCRIPTION = "BSD replacement for libreadline"
HOMEPAGE = "http://www.thrysoee.dk/editline/"
SECTION = "libs"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=1e4228d0c5a9093b01aeaaeae6641533"

DEPENDS = "ncurses"

inherit autotools

# upstream site does not allow wget's User-Agent
FETCHCMD_wget += "-U bitbake"
SRC_URI = "http://www.thrysoee.dk/editline/${PN}-${PV}-3.0.tar.gz"

S = "${WORKDIR}/${PN}-${PV}-3.0"

SRC_URI[md5sum] = "f475f50fe7467c1074c0fbae8b5bca1a"
SRC_URI[sha256sum] = "0379e4a73c219f260e6d16adbc2e87635532ebb4c5a83a7399f231dc40108d1f"
