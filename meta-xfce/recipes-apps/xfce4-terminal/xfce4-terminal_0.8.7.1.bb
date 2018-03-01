SUMMARY = "Terminal emulator for the Xfce desktop environment"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "glib-2.0 gtk+3 vte libxfce4ui"

inherit xfce-app

FILES_${PN} += "${datadir}/xfce4 \
                ${datadir}/gnome-control-center"

SRC_URI[md5sum] = "cb34fdf6783d96bebd53e77fd83964ad"
SRC_URI[sha256sum] = "9ff21627e8d2a105e7133efc3e8eeeda376a2071fac737e37cf47a539a7b4351"
