DESCRIPTION = "openbox Window Manager"
SECTION = "x11/wm"
DEPENDS = "glib-2.0 pango libxml2 virtual/libx11"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

PR = "r1"

SRC_URI = "http://icculus.org/openbox/releases/openbox-${PV}.tar.gz \
           file://fix-dialog-buttons.patch;patch=1 \
           file://fix-decorations.patch;patch=1"

SRC_URI[md5sum] = "30e669134fa81df810fe7d1dc59cd931"
SRC_URI[sha256sum] = "2e7579389c30e6bb08cc721a2c1af512e049fec2670e71715aa1c4e129ec349d"

inherit autotools gettext update-alternatives

ALTERNATIVE_PATH = "${bindir}/openbox"
ALTERNATIVE_NAME = "x-window-manager"
ALTERNATIVE_LINK = "${bindir}/x-window-manager"
ALTERNATIVE_PRIORITY = "10"

EXTRA_OECONF += "--with-plugins=none"

PACKAGES =+ "${PN}-core ${PN}-lxde ${PN}-gnome"

PACKAGES_DYNAMIC += "${PN}-theme-*"

python populate_packages_prepend() {
	theme_dir = bb.data.expand('${datadir}/themes/', d)
	theme_name = bb.data.expand('${PN}-theme-%s', d)
	do_split_packages(d, theme_dir, '(.*)', theme_name, '${PN} theme for %s', extra_depends='', allow_dirs=True)
}

RDEPENDS_${PN} += "${PN}-core"
FILES_${PN}-core = "${bindir}/openbox ${libdir}/*${SOLIBS}"

FILES_${PN}-lxde += "${datadir}/lxde/ \
                     ${datadir}/lxpanel \
                     ${datadir}/xsessions \
                     ${datadir}/icons"

FILES_${PN}-gnome += "${datadir}/gnome/"
