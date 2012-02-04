DESCRIPTION = "Power manager for the Xfce desktop environment"
HOMEPAGE = "http://goodies.xfce.org/projects/applications/xfce4-power-manager"
SECTION = "x11"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

inherit xfce

DEPENDS = "gtk+ glib-2.0 dbus-glib xfconf libxfce4ui libxfce4util libnotify \
           libxrandr virtual/libx11 libxext xfce4-panel"
RDEPENDS_${PN} = "networkmanager udisks upower polkit"

EXTRA_OECONF = " \
    --enable-polkit \
    --enable-network-manager \
    --enable-panel-plugins \
    "

PACKAGES += "xfce4-brightness-plugin"
FILES_${PN} += "${datadir}/polkit-1"
FILES_xfce4-brightness-plugin = " \
    ${libdir}/xfce4/panel-plugins/xfce4-brightness-plugin \
    ${datadir}/xfce4/panel-plugins/xfce4-brightness-plugin.desktop \
    "

SRC_URI[md5sum] = "38cbd272eb30e36ae538d9f38858bd38"
SRC_URI[sha256sum] = "80e4bb44f81c485ec09654dde432d6e28667d49a787625632a4413aac30422f0"
