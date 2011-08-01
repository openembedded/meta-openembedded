DESCRIPTION = "File manager for the Xfce Desktop Environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "exo gtk+ gdk-pixbuf libxfce4util libxfce4ui libsm dbus-glib startup-notification libnotify"

PR = "r0"

inherit xfce

# SRC_URI must follow inherited one
SRC_URI = "http://archive.xfce.org/src/xfce/${PN}/${@'${PV}'[0:3]}/Thunar-${PV}.tar.bz2 \
           file://relative-symlinks-docs.patch \
"

S = "${WORKDIR}/Thunar-${PV}/"


# no/minimum(default)/yes/full(fails <- Werror)
#EXTRA_OECONF += "--enable-debug=yes"

FILES_${PN} += "${libdir}/thunarx-2/* \
                ${libdir}/xfce4/panel/plugins/* \
                ${libdir}/Thunar/[Tt]hunar* \
                ${datadir}/dbus-1 \
                ${datadir}/Thunar \
                ${datadir}/xfce4/panel-plugins/*"

FILES_${PN}-dbg += "${libdir}/thunarx-2/.debug/ \
                    ${libdir}/xfce4/panel/plugins/.debug/ \
                    ${libdir}/Thunar/.debug/"

SRC_URI[md5sum] = "a86df0212db71e61f459bda6bc7b7fb6"
SRC_URI[sha256sum] = "56b07dddde423ae7854c5edd433948a912c27afe2a60689ffc50df3d981e90f6"
