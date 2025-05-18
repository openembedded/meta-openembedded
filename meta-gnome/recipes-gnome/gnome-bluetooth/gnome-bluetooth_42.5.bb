SUMMARY = "GNOME bluetooth manager"
LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=eb723b61539feef013de476e68b5c50a \
    file://COPYING.LIB;md5=a6f89e2100d9b6cdffcea4f398e37343 \
"

SECTION = "x11/gnome"

DEPENDS = " \
    udev \
    libnotify \
    libcanberra \
    bluez5 \
    upower \
    gtk4 \
    gsound \
    libadwaita \
"

GNOMEBASEBUILDCLASS = "meson"
GTKDOC_MESON_OPTION = "gtk_doc"
GTKIC_VERSION = "4"

inherit features_check gnomebase gtk-icon-cache gtk-doc gobject-introspection

REQUIRED_DISTRO_FEATURES = "x11 opengl"

SRC_URI[archive.sha256sum] = "a4f5f1ac2dbb7b7b92f7d6d2b42c030f62aed9b5426b9dc116946041f3cba8f7"

BT_PULSE_PACKS = " \
    pulseaudio-lib-bluez5-util \
    pulseaudio-module-bluetooth-discover \
    pulseaudio-module-bluetooth-policy \
    pulseaudio-module-bluez5-device \
    pulseaudio-module-bluez5-discover \
"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'pulseaudio', d)}"
PACKAGECONFIG[pulseaudio] = ",,,${BT_PULSE_PACKS}"

FILES:${PN} += "${datadir}/gnome-bluetooth-3.0"

RDEPENDS:${PN} += "bluez5"
