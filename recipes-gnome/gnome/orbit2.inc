LICENSE = "GPL"
DESCRIPTION = "CORBA ORB"
SECTION = "x11/gnome/libs"
SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/ORBit2/2.14/ORBit2-${PV}.tar.bz2 \
           file://configure-lossage.patch \
           file://pkgconfig-fix.patch \
	   file://gtk-doc.m4 \
	   file://gtk-doc.make"
DEPENDS = "libidl popt orbit2-native gtk-doc"
PARALLEL_MAKE = ""

FILES_${PN} += "${libdir}/orbit-2.0/*.so"
FILES_${PN}-dev += "${libdir}/orbit-2.0/*.a ${libdir}/orbit-2.0/*.la"
FILES_${PN}-dbg += "${libdir}/orbit-2.0/.debug"

S = "${WORKDIR}/ORBit2-${PV}"

LEAD_SONAME = "libORBit-2.so"

inherit autotools pkgconfig

EXTRA_OECONF = "--disable-gtk-doc"
EXTRA_OEMAKE = "IDL_COMPILER='${STAGING_BINDIR_NATIVE}/orbit-idl-2'"

do_configure_prepend() {
	mkdir -p m4
	install ${WORKDIR}/gtk-doc.m4 ./m4/
	install ${WORKDIR}/gtk-doc.make ./
}

