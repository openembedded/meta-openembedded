DESCRIPTION="Application library for the Xfce desktop environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "gtk+ libxfce4util virtual/libx11 perl-native cairo"

PR = "r0"

inherit xfce

# SRC_URI must follow inherited one
SRC_URI += " \
    file://exo-no-tests-0.6.patch \
    file://configure.patch \
    file://gnome-mount \
    file://fix-qa-desktop-type-error.patch \
"


# see http://wiki.xfce.org/gnomemount-replacement
do_install_append () {
	install -m 755 -d ${D}${bindir}/exo-mount-wrapper
	install -m 755 ${WORKDIR}//gnome-mount ${D}${bindir}/exo-mount-wrapper
}

# Note: python bindings did not work in oe-dev and are about to be moved to
# pyxfce see http://comments.gmane.org/gmane.comp.desktop.xfce.devel.version4/19560
FILES_${PN} += "${datadir}/xfce4/ \
                ${libdir}/xfce4/exo-1 \
               "

FILES_${PN}-dbg += "${libdir}/gio/modules/.debug \
                   "

SRC_URI[md5sum] = "e25333df350abc30999cd16fe96ab690"
SRC_URI[sha256sum] = "f49cf6a85546f47a1dddb58ce9a5f9364384a6f173cba99deb879e027c811f39"
