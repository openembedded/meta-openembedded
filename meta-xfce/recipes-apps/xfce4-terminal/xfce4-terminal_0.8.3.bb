SUMMARY = "Terminal emulator for the Xfce desktop environment"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "glib-2.0 gtk+3 vte libxfce4ui"

inherit xfce-app

FILES_${PN} += "${datadir}/xfce4 \
                ${datadir}/gnome-control-center"

SRC_URI[md5sum] = "d4a3211c23aa3f588e86cb21d34c0e91"
SRC_URI[sha256sum] = "40e6e4b3b8e887a326d9491a47388bbdc379f971a8d622509c0a006e53dc12f1"
