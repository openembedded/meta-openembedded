DESCRIPTION = "Window navigation construction toolkit"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5f30f0716dfdd0d91eb439ebec522ec2"

BPN = "libwnck"

SECTION = "x11/libs"
DEPENDS = "gobject-introspection-stub gtk+3 gdk-pixbuf-native"

PR = "r1"

inherit gnome
SRC_URI[archive.md5sum] = "e4ea87320dd0600a81c50186e3804aae"
SRC_URI[archive.sha256sum] = "4946b612c22d53238810d431f1b05c21f073f201edfd247ff74e2fa228618083"
