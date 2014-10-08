SUMMARY = "File manager for the Xfce Desktop Environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "exo glib-2.0 gtk+ gdk-pixbuf libxfce4util libxfce4ui libsm dbus-glib startup-notification libnotify xfce4-panel udev"

inherit xfce

SRC_URI[md5sum] = "4f10d5d5576ce5127308d6badbac3afa"
SRC_URI[sha256sum] = "9a2706f6881ac29cda9f0b0325d179153bc09e37bcbafcab9823c1c1ec89579d"
SRC_URI = "http://archive.xfce.org/src/xfce/${BPN}/${@'${PV}'[0:3]}/Thunar-${PV}.tar.bz2"

S = "${WORKDIR}/Thunar-${PV}/"

PACKAGECONFIG ??= ""
PACKAGECONFIG[pcre] = "--enable-pcre,--disable-pcre,libpcre"

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
