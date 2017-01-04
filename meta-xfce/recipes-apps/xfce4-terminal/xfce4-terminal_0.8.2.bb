SUMMARY = "Terminal emulator for the Xfce desktop environment"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "glib-2.0 gtk+3 vte libxfce4ui"

inherit xfce-app

FILES_${PN} += "${datadir}/xfce4 \
                ${datadir}/gnome-control-center"

SRC_URI[md5sum] = "53d46d7dbcb74d238dc5e0c8dbc447cd"
SRC_URI[sha256sum] = "92aad3e14f3ef8d4c6c1409463e989bd8f5eefb686234f3cdbfe574729401681"
