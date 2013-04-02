DESCRIPTION="Application library for the Xfce desktop environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "gtk+ libxfce4util libxfce4ui virtual/libx11 liburi-perl-native cairo"
DEPENDS_class-native = "glib-2.0-native xfce4-dev-tools-native"
PR = "r2"

inherit xfce pythonnative perlnative

# SRC_URI must follow inherited one
SRC_URI += " \
    file://exo-no-tests-0.8.patch \
    file://configure.patch \
    file://gnome-mount \
"

SRC_URI_append_class-native = " \
    file://reduce-build-to-exo-csource-only.patch \
"

SRC_URI[md5sum] = "c70f2a217811bfba2e62f938d4b8f748"
SRC_URI[sha256sum] = "b385828bf8a38204da3254b57fdfa25a72694495aa189fabd5040f707eec76ce"

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

BBCLASSEXTEND = "native"
