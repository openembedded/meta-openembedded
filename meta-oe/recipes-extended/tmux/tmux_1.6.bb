SUMMARY = "Terminal multiplexer"
HOMEPAGE = "http://tmux.sourceforge.net"
SECTION = "console/utils"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://tmux.c;beginline=3;endline=17;md5=8685b4455330a940fab1ff451aa941a0"

DEPENDS = "ncurses libevent"

SRC_URI = "${SOURCEFORGE_MIRROR}/tmux/${BP}.tar.gz"
SRC_URI[md5sum] = "3e37db24aa596bf108a0442a81c845b3"
SRC_URI[sha256sum] = "faee08ba1bd8c22537cd5b7458881d1bdb4985df88ed6bc5967c56881a7efbd6"

inherit autotools pkgconfig

do_configure_prepend () {
    sed -i -e 's:-I/usr/local/include::' ${S}/Makefile.am || bb_fatal "sed failed"
}
