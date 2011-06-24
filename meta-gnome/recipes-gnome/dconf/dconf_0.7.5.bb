DESCRIPTION = "configuation database system"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

SECTION = "x11/gnome"

inherit gnome
SRC_URI[archive.md5sum] = "d784f8afca04473d194a72047595c28e"
SRC_URI[archive.sha256sum] = "e2103e8207744903790e9fac6427fa394bb485a0c7f4e0d03b0fb43268c34f33"

DEPENDS = "glib-2.0 gtk+3"

inherit vala

PACKAGES =+ "dconf-editor"

FILES_dconf-editor = "${bindir}/dconf-editor ${datadir}/dconf-editor/"
FILES_${PN} += "${datadir}/dbus-1/ \
                ${libdir}/gio/modules/*.so \
               "

pkg_postinst_${PN} () {
if [ -n "$D" ]; then
    exit 1
fi

glib-compile-schemas ${datadir}/glib-2.0/schemas
}
