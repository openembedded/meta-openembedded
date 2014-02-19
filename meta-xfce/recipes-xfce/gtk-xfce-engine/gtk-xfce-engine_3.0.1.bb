SUMMARY = "XFCE theme for GTK"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "gtk+ gtk+3 xfce4-dev-tools-native"

inherit xfce

SRC_URI[md5sum] = "174e774d0debb052ec457640275f065d"
SRC_URI[sha256sum] = "eb03cf81da1a91e426a2141c092ed7a1634cab29ea2f3546480e901290a7a06d"

PACKAGES += "${PN}-themes gtk3-xfce-engine"
FILES_${PN} += "${libdir}/gtk-2.0/*/engines/*.so"
FILES_gtk3-xfce-engine += "${libdir}/gtk-3.0/*/theming-engines/*.so"
FILES_${PN}-themes += "${datadir}/themes"

FILES_${PN}-dbg += "${libdir}/gtk-3.0/*/theming-engines/.debug \
                    ${libdir}/gtk-2.0/*/engines/.debug"
FILES_${PN}-dev += "${libdir}/gtk-2.0/*/engines/*.la \
                    ${libdir}/gtk-3.0/*/theming-engines/*.la"

RDEPENDS_${PN} += "${PN}-themes"
RDEPENDS_gtk3-xfce-engine += "${PN}-themes"
