DESCRIPTION = "pv - Pipe Viewer - is a terminal-based tool for monitoring the progress of data through a pipeline."

LICENSE = "Artistic-License-2.0"
LIC_FILES_CHKSUM = "file://doc/COPYING;md5=9c50db2589ee3ef10a9b7b2e50ce1d02"

SRC_URI = "http://www.ivarch.com/programs/sources/pv-${PV}.tar.bz2"
SRC_URI[md5sum] = "d55ff76f5caa83efc23aa527dbb0b191"
SRC_URI[sha256sum] = "edfea0033ec6222eb60b4ec6d905dd2dccdb5900beef03f67f42ca9ed67e9fe2"

inherit autotools

# broken autotools
do_configure() {
	cp ${S}/autoconf/configure.in ${S}
	gnu-configize --force
	oe_runconf
}
