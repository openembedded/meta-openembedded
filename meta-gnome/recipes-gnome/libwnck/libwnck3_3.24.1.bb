SUMMARY = "Window navigation construction toolkit"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5f30f0716dfdd0d91eb439ebec522ec2"

BPN = "libwnck"

SECTION = "x11/libs"
DEPENDS = "intltool-native gnome-common-native gtk+3 gdk-pixbuf-native libxres"

PACKAGECONFIG ??= "startup-notification"
PACKAGECONFIG[startup-notification] = "--enable-startup-notification,--disable-startup-notification,startup-notification"

inherit gnomebase gobject-introspection gtk-doc gettext

SRC_URI[archive.md5sum] = "23df51ec0a1169014fe3a102d572b244"
SRC_URI[archive.sha256sum] = "afa6dc283582ffec15c3374790bcbcb5fb422bd38356d72deeef35bf7f9a1f04"

inherit distro_features_check
# libxres means x11 only
REQUIRED_DISTRO_FEATURES = "x11"
