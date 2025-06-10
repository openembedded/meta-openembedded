SUMMARY = "Terminal emulator for the Xfce desktop environment"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "glib-2.0 gtk+3 vte libxfce4ui gtk-doc-native"

inherit xfce-app

FILES:${PN} += " \
    ${datadir}/xfce4 \
    ${datadir}/gnome-control-center \
"

SRC_URI[sha256sum] = "40823377242b9b09749f5d4a550f41d465153c235c31f34052f804d3453ad4a3"

RRECOMMENDS:${PN} += "vte-prompt"
