SUMMARY = "File manager for the Xfce Desktop Environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "exo glib-2.0 gtk+ gdk-pixbuf libxfce4util libxfce4ui xfconf libsm dbus-glib startup-notification libnotify xfce4-panel udev"

inherit xfce gtk-doc

SRC_URI = "http://archive.xfce.org/src/xfce/${BPN}/${@'${PV}'[0:3]}/Thunar-${PV}.tar.bz2"
SRC_URI[md5sum] = "d7c6ae92f180c1d8d5b4c3f368142252"
SRC_URI[sha256sum] = "4079ccffe990cdccee48a0ae200d8a5bc9335c34721d7923b375795940cf79bb"

S = "${WORKDIR}/Thunar-${PV}/"

PACKAGECONFIG ??= ""
PACKAGECONFIG[pcre] = "--enable-pcre,--disable-pcre,libpcre"

FILES_${PN} += " \
    ${libdir}/thunarx-2/* \
    ${libdir}/xfce4/panel/plugins/* \
    ${libdir}/Thunar/[Tt]hunar* \
    ${datadir}/appdata \
    ${datadir}/dbus-1 \
    ${datadir}/polkit-1 \
    ${datadir}/Thunar \
    ${datadir}/xfce4/panel/plugins/* \
"

FILES_${PN}-dbg += "${libdir}/thunarx-2/.debug/ \
                    ${libdir}/xfce4/panel/plugins/.debug/ \
                    ${libdir}/Thunar/.debug/"

RRECOMMENDS_${PN} = "gvfs gvfsd-trash udisks"
