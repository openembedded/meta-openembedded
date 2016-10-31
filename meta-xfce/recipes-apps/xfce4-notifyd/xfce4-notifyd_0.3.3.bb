SUMMARY = "Easily themable notification daemon with transparency effects"
HOMEPAGE = "http://goodies.xfce.org/projects/applications/xfce4-notifyd"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "libxfce4util libxfce4ui xfconf gtk+ dbus dbus-glib libnotify"

inherit xfce-app

SRC_URI[md5sum] = "f918bd3f9f9ad85eace408aecf541198"
SRC_URI[sha256sum] = "986c4cfeb64c21c6bfb2fe65839490ad0f501fdfc8ed10cc71815da5da110f73"

do_compile_prepend() {
    mkdir -p xfce4-notifyd
}

FILES_${PN} += " \
    ${systemd_user_unitdir} \
    ${libdir}/xfce4/notifyd \
    ${datadir}/themes \
    ${datadir}/dbus-1 \
"
