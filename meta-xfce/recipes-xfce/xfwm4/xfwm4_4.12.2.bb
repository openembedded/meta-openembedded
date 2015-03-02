DESCRIPTION="Xfce4 Window Manager"
SECTION = "x11/wm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d791728a073bc009b4ffaf00b7599855"
DEPENDS = "virtual/libx11 libxpm gtk+ libxfce4util libxfce4ui xfconf libwnck dbus-glib startup-notification exo-native"

inherit xfce update-alternatives

SRC_URI += "file://0001-don-t-block-display-events-when-time-is-set-backward.patch"
SRC_URI[md5sum] = "3728768dd9ea0de624cd3efc5dcccbca"
SRC_URI[sha256sum] = "5bb5f72b41060d10bd3823f8b69abcd462bbd8853fdf9c82041450ae68e7d75a"

python populate_packages_prepend () {
    themedir = d.expand('${datadir}/themes')
    do_split_packages(d, themedir, '^(.*)', 'xfwm4-theme-%s', 'XFWM4 theme %s', allow_dirs=True)
}

PACKAGES_DYNAMIC += "^xfwm4-theme-.*"

ALTERNATIVE_${PN} = "x-window-manager"
ALTERNATIVE_TARGET[x-window-manager] = "${bindir}/xfwm4"
ALTERNATIVE_PRIORITY[x-window-manager] = "30"

RDEPENDS_${PN} = "xfwm4-theme-default"
FILES_${PN} += "${libdir}/xfce4/xfwm4/helper-dialog \
                ${datadir}/xfwm4/defaults \
"
FILES_${PN}-dbg += "${libexecdir}/xfce4/xfwm4/.debug/*"

