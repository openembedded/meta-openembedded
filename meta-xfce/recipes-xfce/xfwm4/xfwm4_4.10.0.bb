DESCRIPTION="Xfce4 Window Manager"
SECTION = "x11/wm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d791728a073bc009b4ffaf00b7599855"
DEPENDS = "virtual/libx11 libxpm gtk+ libxfce4util libxfce4ui xfconf libwnck dbus-glib startup-notification"

inherit xfce update-alternatives

SRC_URI[md5sum] = "333e5e25a85411c304e9b4474bf00537"
SRC_URI[sha256sum] = "492357bf48121ebffabf2bf0d3b84213d19bf81087321175d687c8a68efe1f9c"

python populate_packages_prepend () {
    themedir = bb.data.expand('${datadir}/themes', d)
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

