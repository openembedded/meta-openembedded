SUMMARY = "GNOME flashback panel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit gnomebase gsettings itstool gnome-help gtk-icon-cache gtk-doc gettext upstream-version-is-even features_check

REQUIRED_DISTRO_FEATURES = "x11 polkit systemd pam"

DEPENDS += " \
    yelp-tools-native \
    libwnck3 \
    polkit \
    dconf \
    libgweather \
    gnome-menus3 \
    gnome-desktop3 \
    gdm \
"

SRC_URI[archive.sha256sum] = "1a8b15aef0a02a6caa9b9209c8d3fb43e7b987a380076691c23f0314ec302f15"

PACKAGECONFIG[eds] = "--enable-eds,--disable-eds,evolution-data-server"

RDEPENDS:${PN} += "gdm-base"
