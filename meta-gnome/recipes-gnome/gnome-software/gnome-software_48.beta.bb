SUMMARY = "GNOME Software allows users to easily find, discover and install apps."
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

GTKDOC_MESON_OPTION ?= "gtk_doc"

inherit gnomebase gsettings itstool gnome-help gtk-icon-cache gtk-doc mime mime-xdg gettext upstream-version-is-even features_check

REQUIRED_DISTRO_FEATURES = "x11 polkit systemd pam"

DEPENDS += " \
	appstream \
	gdk-pixbuf \
	glib-2.0 \
	glib-2.0-native \
	gsettings-desktop-schemas \
	gtk4 \
	iso-codes \
	json-glib \
	libadwaita \
	libgudev \
	libsoup-3.0 \
	libxmlb-native \
	polkit \
"

RDEPENDS:${PN} = "iso-codes"

EXTRA_OEMESON += "-Dtests=false"

SRC_URI[archive.sha256sum] = "798c48912788b3ff700fd483fe805f3e0f0912cf2e46b7f7334c8a9c6f883296"

PACKAGECONFIG ?= "flatpak"
PACKAGECONFIG[flatpak] = "-Dflatpak=true,-Dflatpak=false,flatpak ostree"
PACKAGECONFIG[snap] = "-Dsnap=true,-Dsnap=false,snapd-glib"
PACKAGECONFIG[gtk_doc] = "-Dgtk_doc=true,-Dgtk_doc=false,libxslt-native docbook-xsl-stylesheets"
PACKAGECONFIG[man] = "-Dman=true,-Dman=false,libxslt-native docbook-xsl-stylesheets"
PACKAGECONFIG[packagekit] = "-Dpackagekit=true,-Dpackagekit=false,gnome-packagekit,gnome-packagekit"
PACKAGECONFIG[fwupd] = "-Dfwupd=true,-Dfwupd=false,fwupd,fwupd"
PACKAGECONFIG[malcontent] = "-Dmalcontent=true,-Dmalcontent=false,malcontent"

FILES:${PN} += "${datadir}"
FILES:${PN}-dev += "${libdir}/gnome-software/libgnomesoftware.so"
