DESCRIPTION = "xfce4-session is a session manager for Xfce 4 Desktop Environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ac2e7cff1ddaf48b6eab6028f23ef88"
DEPENDS = "virtual/libx11 libsm libxfce4util libxfce4ui gtk+ libwnck dbus dbus-glib xfconf xfce4-panel gconf gnome-keyring"
RDEPENDS_${PN} = "netbase xinit dbus-x11 iceauth"

PR = "r0"

inherit xfce

# SRC_URI must follow inherited one
SRC_URI += " \
    file://relative-symlinks-docs.patch \
"

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

SRC_URI[md5sum] = "478080ff666fdd36786a243829663efd"
SRC_URI[sha256sum] = "1df52a77d87ed4d27b4f40a2f03a0b6334422d64bdc4e31b9aac22e25e68b829"
