SUMMARY = "Torrent client"
HOMEPAGE = "http://libtorrent.rakshasa.no/"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "autoconf-archive libsigc++-2.0 curl cppunit libtool libtorrent ncurses"

SRC_URI = "git://github.com/rakshasa/rtorrent;branch=master;protocol=https;tag=v${PV} \
           file://0001-checks.m4-Keep-the-atomic-test-program-from-being-opt.patch \
           "

SRCREV = "15ccae27deb9aa1dc8e46ea0ed218a673ccc5732"

inherit autotools pkgconfig

EXTRA_OECONF:append:libc-musl = " --disable-execinfo"
