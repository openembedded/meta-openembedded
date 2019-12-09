SUMMARY = "GNOME Shell Extensions"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4cb3a392cbf81a9e685ec13b88c4c101"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gettext gsettings features_check upstream-version-is-even

REQUIRED_DISTRO_FEATURES = "x11 polkit systemd pam gobject-introspection-data"

SRC_URI[archive.md5sum] = "09e64640bbf0e978171d581e2086a0c6"
SRC_URI[archive.sha256sum] = "b0ad51143b1bf40def4a10f8fd0ab0ffb47218304800794e62cadd29d0acc75b"

DEPENDS += " \
    sassc-native \
"

EXTRA_OEMESON += " \
    -Dextension_set=all \
    -Dclassic_mode=true \
"

RDEPENDS_${PN} += "gnome-shell"

FILES_${PN} += " \
    ${datadir}/gnome-shell \
    ${datadir}/gnome-session \
    ${datadir}/xsessions \
"
