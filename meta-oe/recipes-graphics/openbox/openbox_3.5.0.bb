SUMMARY = "openbox Window Manager"
SECTION = "x11/wm"
DEPENDS = "glib-2.0 pango libxml2 virtual/libx11"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "http://icculus.org/openbox/releases/openbox-${PV}.tar.gz"

SRC_URI[md5sum] = "00441b53cf14c03566c8e82643544ff9"
SRC_URI[sha256sum] = "6fa90016530b3aa6102e254079461977439398531fb23e7ec076ff2c140fea0a"

PR = "r2"

inherit autotools gettext update-alternatives pkgconfig

ALTERNATIVE_${PN}-core = "x-window-manager"
ALTERNATIVE_TARGET[x-window-manager] = "${bindir}/openbox"
ALTERNATIVE_PRIORITY[x-window-manager] = "10"

PACKAGECONFIG ??= ""
PACKAGECONFIG[imlib2] = "--enable-imlib2,--disable-imlib2,imlib2"
PACKAGECONFIG[startup-notification] = "--enable-startup-notification,--disable-startup-notification,startup-notification"
PACKAGECONFIG[xrandr] = "--enable-xrandr,--disable-xrandr,libxrandr"
PACKAGECONFIG[xinerama] = "--enable-xinerama,--disable-xinerama,libxinerama"
PACKAGECONFIG[xcursor] = "--enable-xcursor,--disable-xcursor,libxcursor"

PACKAGES =+ "${PN}-core ${PN}-lxde ${PN}-gnome ${PN}-config"

PACKAGES_DYNAMIC += "^${PN}-theme-.*"

python populate_packages_prepend() {
    theme_dir = d.expand('${datadir}/themes/')
    theme_name = d.expand('${PN}-theme-%s')
    do_split_packages(d, theme_dir, '(.*)', theme_name, '${PN} theme for %s', extra_depends='', allow_dirs=True)
}

RDEPENDS_${PN} += "${PN}-core ${PN}-config"
FILES_${PN}-core = "${bindir}/openbox ${libdir}/*${SOLIBS}"

FILES_${PN}-lxde += "${datadir}/lxde/ \
                     ${datadir}/lxpanel \
                     ${datadir}/xsessions \
                     ${datadir}/icons"

FILES_${PN}-gnome += "${datadir}/gnome/"

FILES_${PN}-config += "${sysconfdir}"
