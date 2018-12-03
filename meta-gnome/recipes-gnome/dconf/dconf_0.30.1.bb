SUMMARY = "configuation database system"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"
SECTION = "x11/gnome"

SRC_URI[archive.md5sum] = "a959eef51c917b3c57cfbef1448a375e"
SRC_URI[archive.sha256sum] = "549a3a7cc3881318107dc48a7b02ee8f88c9127acaf2d47f7724f78a8f6d02b7"

DEPENDS = "dbus glib-2.0 intltool-native"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase bash-completion vala

SRC_URI += "file://0001-meson.build-do-not-compile-docs.patch \
           "

FILES_${PN} += " \
    ${datadir}/dbus-1 \
    ${libdir}/gio/modules/*.so \
"
