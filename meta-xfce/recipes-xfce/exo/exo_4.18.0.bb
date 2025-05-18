DESCRIPTION = "Application library for the Xfce desktop environment"
SECTION = "x11"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "gtk+3 libxfce4ui liburi-perl-native cairo"

inherit xfce perlnative gtk-doc features_check mime-xdg

# xfce4 depends on libwnck3, gtk+3 and libepoxy need to be built with x11 PACKAGECONFIG.
# cairo would at least needed to be built with xlib.
ANY_OF_DISTRO_FEATURES = "${GTK3DISTROFEATURES}"

# SRC_URI must follow inherited one
SRC_URI += " \
    file://exo-no-tests-0.8.patch \
"

SRC_URI[sha256sum] = "4f2c61d045a888cdb64297fd0ae20cc23da9b97ffb82562ed12806ed21da7d55"

# Note: python bindings did not work in oe-dev and are about to be moved to
# pyxfce see http://comments.gmane.org/gmane.comp.desktop.xfce.devel.version4/19560
FILES:${PN} += " \
    ${datadir}/xfce4/ \
    ${libdir}/xfce4/exo* \
"
