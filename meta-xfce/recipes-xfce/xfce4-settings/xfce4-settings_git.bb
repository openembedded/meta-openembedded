SUMMARY = "Xfce4 settings"
SECTION = "x11/wm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "exo exo-native garcon gtk+ libxfce4util libxfce4ui xfconf dbus-glib libxi virtual/libx11 xrandr libnotify libxcursor libxklavier upower"

inherit xfce xfce-git

SRC_URI = " git://github.com/schnitzeltony/xfce4-settings.git;protocol=git;branch=for-oe-4.12.0 \
            file://0001-xsettings.xml-remove-trouble-causing-comment.patch \
            file://0002-xsettings.xml-Set-default-themes.patch \
"
SRCREV = "a4540c2350cecf53f7095027edd102c6a24e36af"
S = "${WORKDIR}/git"
PV = "4.12.0+git${SRCPV}"
 
EXTRA_OECONF += "--enable-maintainer-mode --disable-debug"

PACKAGECONFIG ??= " \
    ${@base_contains('DISTRO_FEATURES','systemd','datetime-setter','',d)} \
    ${@base_contains('DISTRO_FEATURES','alsa','sound-setter', base_contains('DISTRO_FEATURES','pulseaudio','sound-setter','',d),d)} \
"
PACKAGECONFIG[datetime-setter] = "--enable-datetime-settings, --disable-datetime-settings,, tzdata"
PACKAGECONFIG[notify] = "--enable-libnotify,--disable-libnotify,libnotify"
PACKAGECONFIG[sound-setter] = "--enable-sound-settings, --disable-sound-settings, libcanberra, libcanberra-gtk2 sound-theme-freedesktop"

FILES_${PN} += " \
    ${libdir}/xfce4 \
    ${datadir}/xfce4 \
"

RRECOMMENDS_${PN} += "gnome-icon-theme"
RRECOMMENDS_${PN} += "${@base_contains('DISTRO_FEATURES','alsa','libcanberra-alsa','',d)}"
RRECOMMENDS_${PN} += "${@base_contains('DISTRO_FEATURES','pulseaudio','libcanberra-pulse','',d)}"
