SUMMARY = "Xfce4 Panel"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=26a8bd75d8f8498bdbbe64a27791d4ee"
DEPENDS = "garcon exo gtk+ dbus cairo virtual/libx11 libxml2 libwnck3 vala-native"

inherit xfce gtk-doc gobject-introspection distro_features_check remove-libtool

REQUIRED_DISTRO_FEATURES = "x11"

SRC_URI[md5sum] = "bb18c10b44df2d9afc30abe6bd2f63df"
SRC_URI[sha256sum] = "42411fee7675bb6fb3055395673dc925d43495df93b5e940a1ae3d0a3d2f63a8"
SRC_URI += " \
    file://0001-windowmenu-do-not-display-desktop-icon-when-no-windo.patch \
    file://0001-use-lxdm-to-replace-dm-tool.patch \
"

python populate_packages_prepend() {
    plugin_dir = d.expand('${libdir}/xfce4/panel/plugins/')
    plugin_name = d.expand('${PN}-plugin-%s')
    do_split_packages(d, plugin_dir, '^lib(.*).so$', plugin_name,
                      '${PN} plugin for %s', extra_depends='', prepend=True,
                      aux_files_pattern=['${datadir}/xfce4/panel/plugins/%s.desktop',
                                         '${sysconfdir}/xdg/xfce/panel/%s-*',
                                         '${datadir}/icons/hicolor/48x48/apps/*-%s.png',
                                         '${bindir}/*%s*'])
}

PACKAGES_DYNAMIC += "^${PN}-plugin-.*"

PACKAGES =+ "${PN}-gtk3"

FILES_${PN} += "${libdir}/xfce4/panel/migrate \
                ${libdir}/xfce4/panel/wrapper-1.0"

FILES_${PN}-dev += "${libdir}/xfce4/panel/plugins/*.la"

FILES_${PN}-gtk3 = " \
    ${libdir}/libxfce4panel-2.0${SOLIBS} \
    ${libdir}/xfce4/panel/wrapper-2.0 \
"
