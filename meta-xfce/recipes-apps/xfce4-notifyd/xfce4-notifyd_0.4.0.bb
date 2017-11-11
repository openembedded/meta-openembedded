SUMMARY = "Easily themable notification daemon with transparency effects"
HOMEPAGE = "http://goodies.xfce.org/projects/applications/xfce4-notifyd"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = " \
    dbus \
    dbus-glib \
    dbus-glib-native \
    libnotify \
    libxfce4util \
    libxfce4ui \
    xfconf \
    xfce4-panel \
"

inherit xfce-app

SRC_URI += "file://0001-Add-a-configure-option-to-start-daemon-by-autostart-.patch"
SRC_URI[md5sum] = "c2de09c30b06eaf2d3faaf3e509d656a"
SRC_URI[sha256sum] = "2771871f67effc0bb80f656cf4aa3cd71fe0ea0f4c04b5d8e97bb1752faf36c9"

# Avoid trouble with other desktops e.g KDE which also ships dbus service named
# org.freedesktop.Notifications
EXTRA_OECONF = "--disable-dbus-start-daemon"

do_compile_prepend() {
    mkdir -p xfce4-notifyd
}

FILES_${PN} += " \
    ${systemd_user_unitdir} \
    ${datadir}/xfce4 \
    ${datadir}/themes \
    ${datadir}/dbus-1 \
    ${libdir}/xfce4 \
"
