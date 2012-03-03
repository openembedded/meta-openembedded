DESCRIPTION = "xfce4-session is a session manager for Xfce 4 Desktop Environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ac2e7cff1ddaf48b6eab6028f23ef88"
DEPENDS = "virtual/libx11 libsm libxfce4util libxfce4ui gtk+ libwnck dbus dbus-glib xfconf xfce4-panel gconf gnome-keyring"
RDEPENDS_${PN} = "netbase xinit dbus-x11 iceauth"

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

FILES_${PN}-staticdev += "${libdir}/xfce4/*/*/*.*a"

SRC_URI[md5sum] = "461cc38bbd37cab881adbdf943f9a402"
SRC_URI[sha256sum] = "f0801b8c0ffa7e5d41b29b8df281ac127adf467bf50e8ded8aebe5a02bd99338"
