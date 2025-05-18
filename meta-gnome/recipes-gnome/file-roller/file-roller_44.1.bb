SUMMARY = "An archive manager utility for the  GNOME Environment"
LICENSE="GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"


DEPENDS = " \
    desktop-file-utils-native \
    glib-2.0-native \
    glib-2.0 \
    json-glib \
    gtk4 \
    libadwaita \
    libarchive \
    libhandy \
    libportal \
"

inherit gnomebase gsettings itstool gobject-introspection gnome-help gettext upstream-version-is-even mime-xdg gtk-icon-cache features_check

REQUIRED_DISTRO_FEATURES = "opengl"

EXTRA_OEMESON += "-Dintrospection=enabled"

SRC_URI = "git://gitlab.gnome.org/GNOME/file-roller.git;protocol=https;branch=master"
SRCREV = "299e86c77d8fe600ee6658357e209b15c674397e"
S = "${WORKDIR}/git"

PACKAGECONFIG ??= ""

PACKAGECONFIG[nautilus] = "-Dnautilus-actions=enabled,-Dnautilus-actions=disabled,nautilus"
PACKAGECONFIG[packagekit] = "-Dpackagekit=true,-Dpackagekit=false,,packagekit"

FILES:${PN} += " \
    ${datadir}/dbus-1 \
    ${datadir}/metainfo \
    ${libdir}/nautilus \
"

EXTRA_OEMESON += "--cross-file=${WORKDIR}/meson-${PN}.cross"

do_write_config:append() {
    cat >${WORKDIR}/meson-${PN}.cross <<EOF
[binaries]
cpio = '${bindir}/cpio'
EOF
}
