SUMMARY = "Terminal emulator for the Xfce desktop environment"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "glib-2.0 gtk+3 vte libxfce4ui"

inherit xfce-app

FILES_${PN} += "${datadir}/xfce4 \
                ${datadir}/gnome-control-center"

SRC_URI[md5sum] = "cb40a6cc5b3e35fa4d68f1b702f11027"
SRC_URI[sha256sum] = "247683a51a964cfaa6b1e92030afe9f782efebfcb550a464170b53eb94216795"

RRECOMMENDS_${PN} += "vte-prompt"
