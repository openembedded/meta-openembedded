DESCRIPTION = "An HTTP library implementation in C"
HOMEPAGE = "http://www.gnome.org/"
BUGTRACKER = "https://bugzilla.gnome.org/"

LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5f30f0716dfdd0d91eb439ebec522ec2"

PR = "r1"

# unstable version
DEFAULT_PREFERENCE = "-1"

SECTION = "x11/gnome/libs"

DEPENDS = "glib-2.0 gnutls libxml2 libproxy sqlite3 libgnome-keyring"

SRC_URI = "${GNOME_MIRROR}/libsoup/2.37/libsoup-${PV}.tar.xz"

SRC_URI[md5sum] = "7737dd5238efd593452ba3c365e63e8c"
SRC_URI[sha256sum] = "df82c51b67f67c3128979d1f3bf20a8ceeea369b6e43aceb16d576d8fc4e8423"

S = "${WORKDIR}/libsoup-${PV}"

inherit autotools pkgconfig

