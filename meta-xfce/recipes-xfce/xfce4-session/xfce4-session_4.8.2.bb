DESCRIPTION = "xfce4-session is a session manager for Xfce 4 Desktop Environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ac2e7cff1ddaf48b6eab6028f23ef88"
DEPENDS = "virtual/libx11 libsm libxfce4util libxfce4ui gtk+ libwnck dbus dbus-glib xfconf xfce4-panel gconf gnome-keyring"
RDEPENDS_${PN} = "netbase xinit dbus-x11 iceauth"

PR = "r0"

inherit xfce

# protect from frightening message that xfce might not work correctly
pkg_postinst_${PN} () {
    echo 127.0.0.1    ${MACHINE} >> /etc/hosts
}

FILES_${PN} += "${libdir}/xfce4/*/*/*.so \
                ${libdir}/xfce4/session/*-*-* \
                ${datadir}/xfce4/*/* \
                ${datadir}/themes/Default/balou/*"

FILES_${PN}-dbg += "${libdir}/xfce4/*/*/.debug"

FILES_${PN}-dev += "${libdir}/xfce4/*/*/*.*a"

SRC_URI[md5sum] = "48780cbcf784ab64debc9312f17765f2"
SRC_URI[sha256sum] = "31bca2a559e05a8a859f150394a901517e5842414ef171a85c5da234e344c0d0"
