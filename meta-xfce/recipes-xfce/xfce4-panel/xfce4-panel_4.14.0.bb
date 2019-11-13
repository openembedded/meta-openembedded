SUMMARY = "Xfce4 Panel"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=26a8bd75d8f8498bdbbe64a27791d4ee"
DEPENDS = "garcon exo gtk+3 cairo virtual/libx11 libxml2 libwnck3 vala-native"

inherit xfce gtk-doc gobject-introspection distro_features_check remove-libtool

REQUIRED_DISTRO_FEATURES = "x11"

SRC_URI[md5sum] = "940e8b430e28093a2e56e390055bcd51"
SRC_URI[sha256sum] = "d5f8f3bd7e142139987e5e62cda26135a424baabb5e47aa0c23b4169d0a66ef4"
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
