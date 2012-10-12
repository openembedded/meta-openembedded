DESCRIPTION = "Xfce4 Panel"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=26a8bd75d8f8498bdbbe64a27791d4ee"
DEPENDS = "libxfce4util garcon libxfce4ui xfconf exo gtk+ dbus cairo virtual/libx11 libxml2 libwnck"

inherit xfce

SRC_URI[md5sum] = "cf7351a4b952dbe3fc5ff509c68def33"
SRC_URI[sha256sum] = "3321f998af2bbd14ba68654a8881774f6ea2ec4f1a3544598e7f47d3ed0009b9"

python populate_packages_prepend() {
    plugin_dir = bb.data.expand('${libdir}/xfce4/panel/plugins/', d)
    plugin_name = bb.data.expand('${PN}-plugin-%s', d)
    do_split_packages(d, plugin_dir, '^lib(.*).so$', plugin_name,
                      '${PN} plugin for %s', extra_depends='', prepend=True,
                      aux_files_pattern=['${datadir}/xfce4/panel/plugins/%s.desktop',
                                         '${sysconfdir}/xdg/xfce/panel/%s-*',
                                         '${datadir}/icons/hicolor/48x48/apps/*-%s.png',
                                         '${bindir}/*%s*'])
}

PACKAGES_DYNAMIC += "^${PN}-plugin-.*"

FILES_${PN} += "${libdir}/xfce4/panel/migrate \
                ${libdir}/xfce4/panel/wrapper"

FILES_${PN}-dbg += "${libdir}/xfce4/panel/plugins/.debug \
		   "
