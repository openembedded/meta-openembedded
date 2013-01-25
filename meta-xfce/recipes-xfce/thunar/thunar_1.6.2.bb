DESCRIPTION = "File manager for the Xfce Desktop Environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "exo glib-2.0 gtk+ gdk-pixbuf libxfce4util libxfce4ui libsm dbus-glib startup-notification libnotify xfce4-panel"
PR = "r1"

inherit xfce

SRC_URI[md5sum] = "a446103ab90855cc8ba484dfeedb82d8"
SRC_URI[sha256sum] = "7dbd172ae396e7a2533f47dc1ff703a9ea1a61af3edfdc6f4877b9b9331abd85"
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

RRECOMMENDS_${PN} = "gvfs gvfsd-trash udisks"
