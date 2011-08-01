DESCRIPTION = "XFCE theme for GTK"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS += "gtk+"

PR = "r0"

inherit xfce

FILES_${PN} += "${libdir}/gtk-2.0/*/engines \
                ${datadir}/themes/"
FILES_${PN}-dbg += "${libdir}/gtk-2.0/*/engines/.debug"

SRC_URI[md5sum] = "8a6527b61b0554cda11d06f66a567314"
SRC_URI[sha256sum] = "7cb0a52d74cded5c6a86894babd214de723bcf68771195db6d94acf5161be617"
