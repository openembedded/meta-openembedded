SUMMARY = "Terminal emulator for the Xfce desktop environment"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "glib-2.0 gtk+3 vte libxfce4ui"

inherit xfce-app

FILES_${PN} += "${datadir}/xfce4 \
                ${datadir}/gnome-control-center"

SRC_URI[md5sum] = "46d347c0ecbb74a7878d48833042371d"
SRC_URI[sha256sum] = "ddfe53a89d315a4a9170ca6d2cee2d33145bd63630062b2e867fb3a5fcde5fdf"
