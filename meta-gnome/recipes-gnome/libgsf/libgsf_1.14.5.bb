DESCRIPTION = "GNOME Structured File Library"
LICENSE = "GPL"
SECTION = "libs"
PR = "r1"

DEPENDS= "libxml2 bzip2 glib-2.0 zlib"
RDEPENDS_${PN} = "gconf gnome-vfs"


PACKAGES =+ "${PN}-gnome ${PN}-gnome-dev "

FILES_${PN}-gnome = "${libdir}/libgsf-gnome-1.so.*"
FILES_${PN}-gnome-dev = "${libdir}/libgsf-gnome-1.* ${includedir}/libgsf-1/gsf-gnome"

inherit autotools pkgconfig gnome gconf

EXTRA_OECONF = "\
		--without-python \
		--without-gnome \
		--disable-gtk-doc \
		--with-bz2"
