DESCRIPTION = "Lightweight, desktop independent GTK+ archive manager"
HOMEPAGE = "http://xarchiver.sourceforge.net"
SECTION = "x11"
PR = "r2"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "gtk+ glib-2.0 xfce4-dev-tools-native"

SRC_URI = "${SOURCEFORGE_MIRROR}/${PN}/${P}.tar.bz2"
SRC_URI[md5sum] = "2bc7f06403cc6582dd4a8029ec9d038d"
SRC_URI[sha256sum] = "cea932ff9d505969201fd502470bbebbc5726ab3d6765e142fc8295aa677ad2a"

inherit gettext pkgconfig autotools gtk-icon-cache

RRECOMMENDS_${PN} = "lzop zip tar bzip2 unzip"
