SUMMARY = "GNOME Settings"
DESCRIPTION = "GNOME Settings is GNOME's main interface for configuration of various aspects of your desktop"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=75859989545e37968a99b631ef42722e"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gsettings gettext vala upstream-version-is-even bash-completion features_check

DEPENDS = " \
    gdk-pixbuf-native \
    colord-gtk \
    udisks2 \
    upower \
    polkit \
    pulseaudio \
    accountsservice \
    samba \
    gsettings-desktop-schemas \
    gnome-settings-daemon \
    gnome-desktop \
    gnome-online-accounts \
    libnma \
    gnome-bluetooth \
    grilo \
    libgtop \
    gsound \
    libpwquality \
"

REQUIRED_DISTRO_FEATURES += "polkit pulseaudio systemd x11"

SRC_URI[archive.sha256sum] = "ea0c71484c65ce2cc11376f9b01e6211fa4a7ffd334f4307fc52c93f0fddd4c7"
SRC_URI += "file://0001-Add-meson-option-to-pass-sysroot.patch"

PACKAGECONFIG ??= "ibus ${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)}"
PACKAGECONFIG[ibus] = "-Dibus=true, -Dibus=false, ibus"
PACKAGECONFIG[wayland] = "-Dwayland=true, -Dwayland=false, wayland"

# Once we have (lib)cheese we can make cheese a PACKAGECONFIG
EXTRA_OEMESON = " \
    -Doe_sysroot=${STAGING_DIR_HOST} \
    -Dcheese=false \
"

FILES:${PN} += " \
    ${datadir}/dbus-1 \
    ${datadir}/gnome-shell \
    ${datadir}/metainfo \
"

FILES:${PN}-dev += "${datadir}/gettext"

RDEPENDS:${PN} += "gsettings-desktop-schemas"
