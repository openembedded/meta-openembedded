DESCRIPTION = "GNOME configuration database system"
SECTION = "x11/gnome"
DEPENDS = "gobject-introspection-native gtk+ orbit2 glib-2.0 libxml2 polkit"

PR = "r4"

LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=55ca817ccb7d5b5b66355690e9abc605"

S = "${WORKDIR}/GConf-${PV}"

SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/GConf/2.32/GConf-${PV}.tar.bz2;name=archive \
           file://02_fix_wrong_return_value.patch \
           file://0001-Port-gconfd-2-to-GDBus.patch \
          "
SRC_URI[archive.md5sum] = "b4475bb58c51ca59c7781cd95b302c13"
SRC_URI[archive.sha256sum] = "46030c09422603dbb72f13b5dd592dcc01fbc13562b9e94dafb2b58982bc6b3a"


EXTRA_OECONF = " --without-openldap --disable-gtk-doc --enable-gtk --with-gtk=2.0 --enable-gsettings-backend=yes POLKIT_POLICY_FILE_VALIDATE=true"
ORBIT_IDL_SRC = "${STAGING_BINDIR_NATIVE}/orbit-idl-2"

inherit autotools gettext

EXTRA_OEMAKE = 'ORBIT_IDL="${ORBIT_IDL_SRC}"'

do_install_append() {
	# this directory need to be created to avoid an Error 256 at gdm launch
	install -d ${D}${sysconfdir}/gconf/gconf.xml.system

	# this stuff is unusable
	rm ${D}${libdir}/GConf/*/*.*a
}

RDEPENDS_${PN} += "dbus-x11"
FILES_${PN} += "${libdir}/GConf/* \
		${libdir}/gio/*/*.so \
		${datadir}/polkit* \
		${datadir}/dbus-1/services/*.service \
		${datadir}/dbus-1/system-services/*.service \
"

FILES_${PN}-dbg += "${libdir}/*/*/.debug"
FILES_${PN}-dev += "${datadir}/sgml/gconf/gconf-1.0.dtd"
