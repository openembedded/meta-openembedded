SUMMARY = "Easily themable notification daemon with transparency effects"
HOMEPAGE = "http://goodies.xfce.org/projects/applications/xfce4-notifyd"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "libxfce4util libxfce4ui xfconf gtk+ dbus dbus-glib libnotify \
    dbus-glib-native \
"

inherit xfce-app

SRC_URI[md5sum] = "793490b488c3c4a3f97d644431f048d8"
SRC_URI[sha256sum] = "038535de79b7056058e8d6dceb457959949831853c2594a686ec68b037ae31bf"

do_compile_prepend() {
    mkdir -p xfce4-notifyd
}

FILES_${PN} += " \
    ${systemd_user_unitdir} \
    ${libdir}/xfce4/notifyd \
    ${datadir}/themes \
    ${datadir}/dbus-1 \
"
