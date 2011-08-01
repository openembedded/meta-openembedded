DESCRIPTION = "Xfce4 Desktop Manager"
SECTION = "x11/base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "gtk+ libxfce4util libxfce4ui libwnck xfconf dbus-glib thunar garcon exo"

PR = "r0"

inherit xfce

# SRC_URI must follow inherited one
SRC_URI += " \
    file://relative-symlinks-docs.patch \
"

FILES_${PN} += "${datadir}/xfce4/backdrops"

SRC_URI[md5sum] = "ed25d59f478afda552d121e96657d16f"
SRC_URI[sha256sum] = "9d0a48c4e9ed6723a3f9a1c5303e2bbe9b04a3b483c52472da46881df4595e71"
