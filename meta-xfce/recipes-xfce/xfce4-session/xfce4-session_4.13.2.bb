SUMMARY = "xfce4-session is a session manager for Xfce 4 Desktop Environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ac2e7cff1ddaf48b6eab6028f23ef88"
DEPENDS = "virtual/libx11 libsm libxfce4util libxfce4ui gtk+ libwnck3 dbus dbus-glib xfconf polkit"
RDEPENDS_${PN} = "netbase xinit dbus-x11 iceauth upower xrdb"

inherit xfce update-alternatives distro_features_check

REQUIRED_DISTRO_FEATURES = "x11"

SRC_URI += " \
    file://0001-configure.in-hard-code-path-to-iceauth.patch \
"
SRC_URI[md5sum] = "1306b6166f47cdf6e0c61259abbb621f"
SRC_URI[sha256sum] = "c0be0c7e602c962d0e8fca63bd86165e60313d07bfb72cf2c3f99ab53e2a22a1"

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
