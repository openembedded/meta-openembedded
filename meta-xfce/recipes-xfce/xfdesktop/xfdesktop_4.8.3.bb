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

SRC_URI[md5sum] = "617c667c469698e8c974e38412cb484c"
SRC_URI[sha256sum] = "e58460f52ae96c389402f6db62c46db61cd51cdb550a42bd97c0ab2a5b62f424"
