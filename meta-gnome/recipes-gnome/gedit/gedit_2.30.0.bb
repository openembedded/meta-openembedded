DESCRIPTION = "GNOME editor"
SECTION = "x11/gnome"
LICENSE = "GPLv2+"

DEPENDS = "enchant gconf gnome-common gnome-doc-utils-native glib-2.0 gtk+ gtksourceview2 iso-codes virtual/gettext"

LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit gnome
SRC_URI[archive.md5sum] = "aebd49797406fdde7b25624b71990860"
SRC_URI[archive.sha256sum] = "37598473372aab217e46f19726cff616ff0ea4121bbdbb170b4e264a4ca76690"

EXTRA_OECONF = "--disable-scrollkeeper"

do_configure_prepend () {
	cp ${STAGING_DATADIR}/gnome-common/data/omf.make ${S}
}

FILES_${PN} += "${libdir}/gedit-2/plugin* ${datadir}/gedit-2"
FILES_${PN}-dbg += "${libdir}/gedit-2/plugin-loaders/.debug ${libdir}/gedit-2/plugins/.debug"

