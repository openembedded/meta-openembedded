DESCRIPTION="Application library for the Xfce desktop environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "gtk+ libxfce4util libxfce4ui virtual/libx11 perl-native cairo"

inherit xfce pythonnative

# SRC_URI must follow inherited one
SRC_URI += " \
    file://exo-no-tests-0.8.patch \
    file://configure.patch \
    file://gnome-mount \
"

SRC_URI[md5sum] = "189bd19210e1d7d8601be1cdf27bf259"
SRC_URI[sha256sum] = "6e21aa30692affcb45fc8283d034da4a02221d26e3e1facb968d04fed4ba05b0"

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
