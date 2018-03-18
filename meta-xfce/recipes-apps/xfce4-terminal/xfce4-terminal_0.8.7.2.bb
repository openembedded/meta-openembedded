SUMMARY = "Terminal emulator for the Xfce desktop environment"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "glib-2.0 gtk+3 vte libxfce4ui"

inherit xfce-app

FILES_${PN} += "${datadir}/xfce4 \
                ${datadir}/gnome-control-center"

SRC_URI[md5sum] = "2a812438aeeb3d76e3d8d790817456bd"
SRC_URI[sha256sum] = "a91fcbb89cab7987abc5d42e32d7ad62d2bf07656e30773eb274c7f087017bf3"
