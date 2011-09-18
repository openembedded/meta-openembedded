DESCRIPTION = "XFCE theme for GTK"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "gtk+ xfce4-dev-tools-native"

PR = "r0"

inherit xfce

FILES_${PN} += "${libdir}/gtk-2.0/*/engines \
                ${datadir}/themes/"
FILES_${PN}-dbg += "${libdir}/gtk-2.0/*/engines/.debug"

SRC_URI[md5sum] = "e2bc76ab5093ff8472e728e6d6ad5da2"
SRC_URI[sha256sum] = "55604002526c800539f2a8a79e7df3bf23717f45e4bfab31c154c8d01caf3af7"
