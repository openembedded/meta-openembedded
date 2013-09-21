DESCRIPTION = "Xfce4 Widget library and X Window System interaction"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=252890d9eee26aab7b432e8b8a616475"
DEPENDS = "gtk+ intltool libxfce4util startup-notification xfconf glade3"

inherit xfce

SRC_URI = " \
    git://git.xfce.org/xfce/libxfce4ui;protocol=git \
    file://0001-libxfce4kbd-private-xfce4-keyboard-shortcuts.xml-fix.patch \
    file://0002-configure.ac.in-remove-optional-gtk3-support.patch \
"
SRCREV = "530b72f50fcbd8cb4b970fcc97be0321bf78183e"
PV = "4.10.0+git${SRCPV}"
S = "${WORKDIR}/git"

EXTRA_OECONF += "--enable-maintainer-mode --disable-debug"

do_configure_prepend() {
       NOCONFIGURE=yes ./autogen.sh
}

FILES_${PN}-dbg += "${libdir}/glade3/modules/.debug"
FILES_${PN}-dev += "${libdir}/glade3/modules/*.la \
                   ${datadir}/glade3/catalogs/*.in"
PACKAGES += "${PN}-glade"
FILES_${PN}-glade = "${libdir}/glade3 \
                     ${datadir}/glade3"
