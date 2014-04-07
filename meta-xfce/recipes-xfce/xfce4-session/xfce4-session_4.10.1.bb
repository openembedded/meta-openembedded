SUMMARY = "xfce4-session is a session manager for Xfce 4 Desktop Environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ac2e7cff1ddaf48b6eab6028f23ef88"
DEPENDS = "virtual/libx11 libsm libxfce4util libxfce4ui gtk+ libwnck dbus dbus-glib xfconf"
RDEPENDS_${PN} = "netbase xinit dbus-x11 iceauth upower"

inherit xfce update-alternatives

SRC_URI[md5sum] = "1757657c1d590aa6274b7b7cbba33352"
SRC_URI[sha256sum] = "0154fabdc398798c3445374ccc52a2f5bcb2d867fc94bc54114395b24f9cfc83"
SRC_URI += " \
    file://0001-configure.in-hard-code-path-to-iceauth.patch \
"

ALTERNATIVE_${PN} = "x-session-manager"
ALTERNATIVE_TARGET[x-session-manager] = "${bindir}/xfce4-session"
ALTERNATIVE_PRIORITY_${PN} = "100"

# protect from frightening message that xfce might not work correctly
pkg_postinst_${PN} () {
    echo 127.0.0.1    ${MACHINE} >> /etc/hosts
}

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES','systemd','systemd','consolekit',d)}"
PACKAGECONFIG[systemd] = "--enable-systemd, --disable-systemd, polkit, systemd"
PACKAGECONFIG[consolekit] = ",,,consolekit"

FILES_${PN} += "${libdir}/xfce4/*/*/*.so \
                ${libdir}/xfce4/session/*-*-* \
                ${datadir}/xsessions \
                ${datadir}/themes/Default/balou/*"

FILES_${PN}-dbg += "${libdir}/xfce4/*/*/.debug"

FILES_${PN}-staticdev += "${libdir}/xfce4/*/*/*.*a"
