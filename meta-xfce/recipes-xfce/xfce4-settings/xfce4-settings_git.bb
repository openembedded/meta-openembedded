SUMMARY = "Xfce4 settings"
SECTION = "x11/wm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "exo exo-native garcon gtk+ libxfce4util libxfce4ui xfconf dbus-glib libxi virtual/libx11 xrandr libnotify libxcursor libxklavier upower"

inherit xfce xfce-git

SRC_URI = " git://gitorious.org/xfce/xfce4-settings.git;protocol=git;branch=for-oe \
            file://0001-xsettings.xml-remove-trouble-causing-comment.patch \
            file://0002-xsettings.xml-Set-default-themes.patch \
            file://touchscreen/invisible \
            file://touchscreen/wait \
            file://touchscreen/0001-add-cursor-theme-xfce-invisible.patch \
            file://touchscreen/0002-mouse-settings-dialog-add-touchscreen-pointer-option.patch \
            file://touchscreen/0003-XfcePointersHelper-gets-a-pointer-to-XfceXSettingsHe.patch \
            file://touchscreen/0004-XfceXSettingsHelper-gets-a-property-touchscreen-poin.patch \
            file://touchscreen/0005-pointers-detect-a-change-of-pointer-device-used-and-.patch \
"
SRCREV = "b7a0e1fd77f5bb5c372223ff62aec7acf252f061"
S = "${WORKDIR}/git"
PV = "4.11.0+git${SRCPV}"
 
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

do_install_prepend() {
    # somehow binary files are not patched correctly by oe-patch - so copy them
    cp ${WORKDIR}/touchscreen/invisible ${S}/cursors
    cp ${WORKDIR}/touchscreen/wait ${S}/cursors
}

RRECOMMENDS_${PN} += "gnome-icon-theme"
RRECOMMENDS_${PN} += "${@base_contains('DISTRO_FEATURES','alsa','libcanberra-alsa','',d)}"
RRECOMMENDS_${PN} += "${@base_contains('DISTRO_FEATURES','pulseaudio','libcanberra-pulse','',d)}"
