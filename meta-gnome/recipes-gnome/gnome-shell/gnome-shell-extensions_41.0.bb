SUMMARY = "GNOME Shell Extensions"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4cb3a392cbf81a9e685ec13b88c4c101"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gettext gsettings features_check

REQUIRED_DISTRO_FEATURES = "x11 polkit systemd pam gobject-introspection-data"

SRC_URI[archive.sha256sum] = "d84faac1448b38f975d9c19490c5928afc5c5a2c49dd7e7f81af690f9466fc68"

DEPENDS += " \
    sassc-native \
"

EXTRA_OEMESON += " \
    -Dextension_set=all \
    -Dclassic_mode=true \
"

RDEPENDS:${PN} += "gnome-shell"

FILES:${PN} += " \
    ${datadir}/gnome-shell \
    ${datadir}/gnome-session \
    ${datadir}/xsessions \
"
