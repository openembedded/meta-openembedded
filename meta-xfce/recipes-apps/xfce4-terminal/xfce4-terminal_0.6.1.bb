DESCRIPTION = "Terminal emulator for the Xfce desktop environment"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "glib-2.0 gtk+ vte libxfce4ui"

inherit xfce-app

FILES_${PN} += "${datadir}/xfce4 \
                ${datadir}/gnome-control-center"

SRC_URI[md5sum] = "98613ce500fef2ed62cdbe788084acca"
SRC_URI[sha256sum] = "61346427e55af79e86511572bfabb2e025cb13720879226ea1b98a92f0bcd4c8"
