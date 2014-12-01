SUMMARY = "GNOME desktop-wide GSettings schemas"
HOMEPAGE = "http://live.gnome.org/gsettings-desktop-schemas"
BUGTRACKER = "https://bugzilla.gnome.org/"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "glib-2.0 intltool-native gobject-introspection-stub-native"

inherit gnomebase gsettings gettext

GNOME_COMPRESS_TYPE = "xz"

SRC_URI[archive.md5sum] = "92d41934212fc7da48648afa3927f4b0"
SRC_URI[archive.sha256sum] = "217eba09f0fb263dcb8ce6efa21c9afaf0504ac1d937732d1af1c6724c05f065"
