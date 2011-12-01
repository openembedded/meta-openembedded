DESCRIPTION = "File manager for the Xfce Desktop Environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "exo gtk+ gdk-pixbuf libxfce4util libxfce4ui libsm dbus-glib startup-notification libnotify"

PR = "r1"

inherit xfce

# SRC_URI must follow inherited one
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

SRC_URI[md5sum] = "0a2c36cdd3cb3a2ab52cbf6055415b85"
SRC_URI[sha256sum] = "e66aa9ad61a9202e15cc7c5fffc7b961da9ab3b4428f874c1ec8e46eb4ffaca6"
