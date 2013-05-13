DESCRIPTION = "Terminal emulator for the Xfce desktop environment"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "glib-2.0 gtk+ vte libxfce4ui"

inherit xfce-app

FILES_${PN} += "${datadir}/xfce4 \
                ${datadir}/gnome-control-center"

SRC_URI[md5sum] = "d5cdb302bd770c9f2d30262c26639006"
SRC_URI[sha256sum] = "3d92422288d26311880af694bb4e02c9235997ca307a5e85001bf6bef65c3b35"
