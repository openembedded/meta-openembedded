DESCRIPTION = "CORBA ORB"
PR = "r0"
LICENSE = "LGPL GPL"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
SECTION = "x11/gnome/libs"
SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/ORBit2/2.14/ORBit2-${PV}.tar.bz2 \
           file://configure-lossage.patch \
	   file://gtk-doc.m4 \
	   file://gtk-doc.make"
DEPENDS = "libidl-native popt-native gtk-doc"

S = "${WORKDIR}/ORBit2-${PV}"
FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/orbit2"

PARALLEL_MAKE = ""
inherit autotools native pkgconfig

EXTRA_OECONF = "--disable-gtk-doc"

do_configure_prepend() {
	mkdir -p m4
	install ${WORKDIR}/gtk-doc.m4 ./m4/
	install ${WORKDIR}/gtk-doc.make ./
}

SRC_URI[md5sum] = "6f4bf7d803d442e9d093a0daa203d506"
SRC_URI[sha256sum] = "d60027a4a36e64d02723d00b76c08e8d92850cab6269b2edcda4a1bb30cc7723"
