DESCRIPTION = "GNOME control center"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "libgnomekbd gnome-menus metacity gstreamer libcanberra gnome-settings-daemon libgnomeui gnome-doc-utils gtk+ libglade libgnomecanvas librsvg libxml2 libart-lgpl atk"

inherit gnome

SRC_URI[archive.md5sum] = "b4e8ab5c7556ae07addbfcfb4fa2f761"
SRC_URI[archive.sha256sum] = "7c568b57358e5c08f4d8dd76dbac7df2539135ad081872b60514b7a8ac797e66"

LDFLAGS += "-lgthread-2.0 -lxml2"

do_configure_prepend() {
	sed -i s:help::g Makefile.am
}

FILES_${PN} += "${datadir}/icon* \
                ${datadir}/xsession* \
                ${libdir}/window-manager-settings/*.so \
                ${datadir}/gnome \
                ${datadir}/desktop-directories \
               "
FILES_${PN}-dbg += "${libdir}/window-manager-settings/.debug"
FILES_${PN}-dev += "${libdir}/window-manager-settings/*a"




