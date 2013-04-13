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

SRC_URI[md5sum] = "935599b7114b0a4b0e2c9a5d6c72524c"
SRC_URI[sha256sum] = "d7fb98a540284b62f4201527de17d4b24123f9d26c9f49131dd497c8387184e9"
