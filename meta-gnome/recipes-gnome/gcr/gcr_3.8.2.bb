SUMMARY = "A library for bits of crypto UI and parsing etc"
HOMEPAGE = "http://www.gnome.org/"
BUGTRACKER = "https://bugzilla.gnome.org/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=55ca817ccb7d5b5b66355690e9abc605"

DEPENDS = "gtk+3 p11-kit glib-2.0 libgcrypt"

PNBLACKLIST[gcr] ?= "CONFLICT: 4 files conflict with gnome-keyring"
# e.g. sysroots/qemux86-64/usr/share/glib-2.0/schemas/org.gnome.crypto.pgp.gschema.xml
#      sysroots/qemux86-64/usr/share/GConf/gsettings/org.gnome.crypto.pgp.convert

inherit autotools gnomebase gtk-icon-cache gtk-doc

GNOME_COMPRESS_TYPE="xz"

SRC_URI[archive.md5sum] = "f5e66afcab19897a0de0590735329fb1"
SRC_URI[archive.sha256sum] = "a1e615ce2cfd375f383bbdd289935a8e3e16901c3c6f032780847055e8bec2c1"

FILES_${PN} += " \
    ${datadir}/dbus-1 \
    ${datadir}/gcr-3 \
"
