DESCRIPTION = "CORBA ORB"
SECTION = "x11/gnome/libs"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
SRC_NAME = "ORBit2"
SHRT_VER = "${@bb.data.getVar('PV',d,1).split('.')[0]}.${@bb.data.getVar('PV',d,1).split('.')[1]}"
SRC_URI = "${GNOME_MIRROR}/${SRC_NAME}/${SHRT_VER}/${SRC_NAME}-${PV}.tar.bz2 \
           file://configure-lossage.patch \
           file://pkgconfig-fix.patch \
	   file://gtk-doc.m4 \
	   file://gtk-doc.make"
SRC_URI[md5sum] = "10bfb957fa4a8935a0b4afaee7d71df7"
SRC_URI[sha256sum] = "62bfce3f678f9347a19c766944e8aef7b89bc32b25ac23eb3e4c25929ce8974c"

PR = "r2"
BBCLASSEXTEND = "native"

IDL_COMPILER_DEPENDS = "orbit2-native"
IDL_COMPILER_DEPENDS_virtclass-native = " "
DEPENDS = "libidl popt gtk-doc ${IDL_COMPILER_DEPENDS}"

# IDL_COMPILER_DEPENDS_virtclass-native for some reason didn't work and orbit2-native
# was still in orbit2-native DEPENDS causing circular dependency
DEPENDS_virtclass-native = "libidl-native popt-native gtk-doc-native"
PARALLEL_MAKE = ""


FILES_${PN} += "${libdir}/orbit-2.0/*.so"
FILES_${PN}-dev += "${libdir}/orbit-2.0/*.a ${libdir}/orbit-2.0/*.la"
FILES_${PN}-dbg += "${libdir}/orbit-2.0/.debug"

S = "${WORKDIR}/${SRC_NAME}-${PV}"

LEAD_SONAME = "libORBit-2.so"

inherit autotools pkgconfig

EXTRA_OECONF = "--disable-gtk-doc"
EXTRA_OEMAKE = "IDL_COMPILER='${STAGING_BINDIR_NATIVE}/orbit-idl-2'"
EXTRA_OEMAKE_virtclass-native = " "

do_configure_prepend() {
	mkdir -p m4
	install ${WORKDIR}/gtk-doc.m4 ./m4/
	install ${WORKDIR}/gtk-doc.make ./
}
