DESCRIPTION = "configuation database system"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

PR = "r1"

SECTION = "x11/gnome"

inherit gnome

DEPENDS = "glib-2.0"

inherit vala

SRC_URI[archive.md5sum] = "c905497d0255fe2ba58564f9655908ab"
SRC_URI[archive.sha256sum] = "0083d70e1b5e540d8d4b3f04fa5d17dff4c574136682fe3bdd9b5ecc196ec4f6"

PACKAGES =+ "dconf-editor"

FILES_dconf-editor = "${bindir}/dconf-editor"
FILES_${PN} += "${datadir}/dbus-1/ \
                ${libdir}/gio/modules/*.so \
               "

