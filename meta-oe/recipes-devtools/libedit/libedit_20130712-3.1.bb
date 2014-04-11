SUMMARY = "BSD replacement for libreadline"
DESCRIPTION = "Command line editor library providing generic line editing, \
history, and tokenization functions"
HOMEPAGE = "http://www.thrysoee.dk/editline/"
SECTION = "libs"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=1e4228d0c5a9093b01aeaaeae6641533"

DEPENDS = "ncurses"

inherit autotools

# upstream site does not allow wget's User-Agent
FETCHCMD_wget += "-U bitbake"
SRC_URI = "http://www.thrysoee.dk/editline/${BPN}-${PV}.tar.gz"

S = "${WORKDIR}/${BPN}-${PV}"

SRC_URI[md5sum] = "0891336c697362727a1fa7e60c5cb96c"
SRC_URI[sha256sum] = "5d9b1a9dd66f1fe28bbd98e4d8ed1a22d8da0d08d902407dcc4a0702c8d88a37"
