DESCRIPTION = "GNOME configuration database system"
SECTION = "x11/gnome"
DEPENDS = "gobject-introspection-native gtk+ orbit2 glib-2.0 libxml2 polkit"
ORBIT_IDL_SRC = "${STAGING_BINDIR_NATIVE}/orbit-idl-2"

LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=55ca817ccb7d5b5b66355690e9abc605"

S = "${WORKDIR}/GConf-${PV}"

SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/GConf/2.32/GConf-${PV}.tar.bz2;name=archive"
SRC_URI[archive.md5sum] = "a7c683fe6566e05a67ecb1ee3b20056c"
SRC_URI[archive.sha256sum] = "63e249d16b1b50820e9c32f1d85ff2c94498afdd45544fa5f37b8e1d084c5bae"

RDEPENDS_${PN} += "dbus-x11"

EXTRA_OECONF = " --without-openldap --disable-gtk-doc --enable-gtk --with-gtk=2.0 POLKIT_POLICY_FILE_VALIDATE=true"

inherit autotools gettext

EXTRA_OEMAKE = 'ORBIT_IDL="${ORBIT_IDL_SRC}"'

do_install_append() {
	# this directory need to be created to avoid an Error 256 at gdm launch
	install -d ${D}${sysconfdir}/gconf/gconf.xml.system

	# this stuff is unusable
	rm ${D}${libdir}/GConf/*/*.*a
}

FILES_${PN} += "${libdir}/GConf/* \
		${libdir}/gio/*/*.so \
		${datadir}/polkit* \
		${datadir}/dbus-1/services/*.service \
		${datadir}/dbus-1/system-services/*.service \
"

FILES_${PN}-dbg += "${libdir}/*/*/.debug"
FILES_${PN}-dev += "${datadir}/sgml/gconf/gconf-1.0.dtd"
