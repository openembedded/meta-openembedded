SUMMARY = "Quickly share a folder using Samba from Thunar"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

inherit thunar-plugin features_check

ANY_OF_DISTRO_FEATURES = "${GTK3DISTROFEATURES}"
REQUIRED_DISTRO_FEATURES = "pam"

SRC_URI[sha256sum] = "0afc9b9ff31c88b208ca9a58bc61d95f9f5408f2adbcd7fe911dfce9e22fba44"

RDEPENDS:${PN} += "samba-server"
