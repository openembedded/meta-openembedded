SUMMARY = "A xdg-desktop-portal backend for gnome."
HOMEPAGE = "https://gitlab.gnome.org/GNOME/xdg-desktop-portal-gnome"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = " \
    glib-2.0-native \
    gtk4\
    xdg-desktop-portal \
    xdg-desktop-portal-gtk \
    libadwaita \
    fontconfig \
    gsettings-desktop-schemas \
    gnome-desktop \
    dconf \
"

RDEPENDS:${PN} = "xdg-desktop-portal xdg-desktop-portal-gtk"

inherit meson pkgconfig gsettings features_check

REQUIRED_DISTRO_FEATURES = "polkit"

SRC_URI = "git://gitlab.gnome.org/GNOME/xdg-desktop-portal-gnome.git;protocol=https;nobranch=1"

S = "${WORKDIR}/git"
SRCREV = "4fd5a8bf16b01c78ed19764415cb26d29aaf7eab"

PACKAGECONFIG ?= ""

PACKAGECONFIG[screenshot] = ",,,gnome-shell"
PACKAGECONFIG[screencast] = ",,,mutter"

FILES:${PN} += "${systemd_user_unitdir} ${datadir}"
