SUMMARY = "Window navigation construction toolkit"
LICENSE = "LGPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gsettings gobject-introspection gettext features_check upstream-version-is-even

DEPENDS = " \
    colord \
    geocode-glib \
    gcr \
    gnome-desktop \
    libgweather4 \
    lcms \
    libcanberra \
    geoclue \
    libnotify \
    upower \
    libwacom \
    virtual/libx11 \
"

# all these are mandatory
REQUIRED_DISTRO_FEATURES = "x11 polkit pulseaudio systemd gobject-introspection-data"
GIR_MESON_OPTION = ""

SRC_URI[archive.sha256sum] = "12653b72d81f151300a47d700ac9410ac1bcce38e83f1bdd19cded2932943989"

PACKAGECONFIG ??= " \
    cups nm \
    alsa gudev \
    smartcard \
    ${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)} \
"
PACKAGECONFIG[alsa] = "-Dalsa=true,-Dalsa=false,alsa-lib"
PACKAGECONFIG[cups] = "-Dcups=true,-Dcups=false,cups"
PACKAGECONFIG[gudev] = "-Dgudev=true,-Dgudev=false,libgudev"
PACKAGECONFIG[nm] = "-Dnetwork_manager=true,-Dnetwork_manager=false,networkmanager"
PACKAGECONFIG[smartcard] = "-Dsmartcard=true,-Dsmartcard=false,nss"
PACKAGECONFIG[wayland] = "-Dwayland=true,-Dwayland=false,wayland"

FILES:${PN} += " \
    ${systemd_user_unitdir} \
    /usr/lib/gnome-settings-daemon-44/libgsd.so \
"
