SUMMARY = "xfce4-session is a session manager for Xfce 4 Desktop Environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ac2e7cff1ddaf48b6eab6028f23ef88"
DEPENDS = "virtual/libx11 libsm libxfce4util libxfce4ui gtk+ libwnck dbus dbus-glib xfconf polkit"
RDEPENDS_${PN} = "netbase xinit dbus-x11 iceauth upower"

inherit xfce update-alternatives

SRC_URI[md5sum] = "d75b257bc0954c238d3c82ce498525aa"
SRC_URI[sha256sum] = "68c85453b097d3ada573dcb7e72acd06e0fc34531996122c10d20096405b7b06"
SRC_URI += " \
    file://0001-configure.in-hard-code-path-to-iceauth.patch \
"

ALTERNATIVE_${PN} = "x-session-manager"
ALTERNATIVE_TARGET[x-session-manager] = "${bindir}/xfce4-session"
ALTERNATIVE_PRIORITY_${PN} = "100"

FILES_${PN} += " \
    ${libdir}/xfce4/*/*/*.so \
    ${libdir}/xfce4/session/*-*-* \
    ${datadir}/xsessions \
    ${datadir}/themes/Default/balou/* \
    ${datadir}/polkit-1 \
"

FILES_${PN}-dbg += "${libdir}/xfce4/*/*/.debug"
