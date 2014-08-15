SUMMARY = "curses-based interface to gdb"
HOMEPAGE = "http://cgdb.github.io/"
SECTION = "devel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "readline ncurses gdb"

SRC_URI = "http://cgdb.me/files/cgdb-${PV}.tar.gz \
	   file://configfix.patch"
SRC_URI[md5sum] = "e2d9a973d2683faf8130a82703bf6a31"
SRC_URI[sha256sum] = "074ed31d1e827a04574add8c27d391447d75313e85ff938005d8ec939499fda9"

EXTRA_OECONF = "--with-readline=${STAGING_LIBDIR} --with-ncurses=${STAGING_LIBDIR}"

inherit autotools

