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
SRC_URI = "http://www.thrysoee.dk/editline/${BPN}-${PV}.tar.gz \
           file://stdc-predef.patch \
          "
SRC_URI[md5sum] = "0467d27684c453a351fbcefebbcb16a3"
SRC_URI[sha256sum] = "0ccbd2e7d46097f136fcb1aaa0d5bc24e23bb73f57d25bee5a852a683eaa7567"

S = "${WORKDIR}/${BPN}-${PV}"
