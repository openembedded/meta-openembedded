DESCRIPTION = "Xfce4 Panel"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=26a8bd75d8f8498bdbbe64a27791d4ee"
DEPENDS = "libxfce4util garcon libxfce4ui xfconf exo gtk+ dbus cairo virtual/libx11 libxml2 libwnck"

PR = "r3"

inherit xfce

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
do_install_append () {
    find "${D}/${libdir}/xfce4/panel/plugins" -name '*.a' -exec rm -f {} +
}

PACKAGES_DYNAMIC = "${PN}-plugin-*"

FILES_${PN} += "${libdir}/xfce4/panel/migrate \
                ${libdir}/xfce4/panel/wrapper"

FILES_${PN}-dbg += "${libdir}/xfce4/panel/plugins/.debug \
		   "
SRC_URI[md5sum] = "9d7bf0503d5867c4044cef04bb5845b1"
SRC_URI[sha256sum] = "66f1266a1242ee5ecada995a4a584dd9802aa24456759b40b0c29227c39bed03"
