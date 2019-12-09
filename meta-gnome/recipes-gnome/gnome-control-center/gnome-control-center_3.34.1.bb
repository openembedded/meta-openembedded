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
    gnome-desktop3 \
    gnome-online-accounts \
    network-manager-applet \
    gnome-bluetooth \
    grilo \
    libgtop \
    gsound \
    libpwquality \
"

REQUIRED_DISTRO_FEATURES += "polkit pulseaudio systemd x11"

SRC_URI[archive.md5sum] = "8b0f0baea34fbe937efd0ad1be4b451b"
SRC_URI[archive.sha256sum] = "5f82ecd5554136c462248ec5363b82d95358b39dfd6787006d8307b4504e515e"
SRC_URI += "file://0001-Add-meson-option-to-pass-sysroot.patch"

PACKAGECONFIG ??= "ibus ${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)}"
PACKAGECONFIG[ibus] = "-Dibus=true, -Dibus=false, ibus"
PACKAGECONFIG[wayland] = "-Dwayland=true, -Dwayland=false, wayland"

# Once we have (lib)cheese we can make cheese a PACKAGECONFIG
EXTRA_OEMESON = " \
    -Doe_sysroot=${STAGING_DIR_HOST} \
    -Dcheese=false \
"

FILES_${PN} += " \
    ${datadir}/dbus-1 \
    ${datadir}/gnome-shell \
    ${datadir}/metainfo \
"

FILES_${PN}-dev += "${datadir}/gettext"

RDEPENDS_${PN} += "gsettings-desktop-schemas"
