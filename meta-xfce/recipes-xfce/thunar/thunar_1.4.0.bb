DESCRIPTION = "File manager for the Xfce Desktop Environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "exo glib-2.0 gtk+ gdk-pixbuf libxfce4util libxfce4ui libsm dbus-glib startup-notification libnotify"

inherit xfce

SRC_URI[md5sum] = "737ad2c36ed36b10fef82e860b9e990a"
SRC_URI[sha256sum] = "2c11a19e64cef708a264fb4d5d933389899c3d132fe7b1e313dd6e37bfe4c8ba"
SRC_URI = "http://archive.xfce.org/src/xfce/${PN}/${@'${PV}'[0:3]}/Thunar-${PV}.tar.bz2"

S = "${WORKDIR}/Thunar-${PV}/"

FILES_${PN} += "${libdir}/thunarx-2/* \
                ${libdir}/xfce4/panel/plugins/* \
                ${libdir}/Thunar/[Tt]hunar* \
                ${datadir}/dbus-1 \
                ${datadir}/Thunar \
                ${datadir}/xfce4/panel-plugins/*"

FILES_${PN}-dbg += "${libdir}/thunarx-2/.debug/ \
                    ${libdir}/xfce4/panel/plugins/.debug/ \
                    ${libdir}/Thunar/.debug/"
