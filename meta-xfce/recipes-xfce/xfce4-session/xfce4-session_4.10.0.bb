DESCRIPTION = "xfce4-session is a session manager for Xfce 4 Desktop Environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ac2e7cff1ddaf48b6eab6028f23ef88"
DEPENDS = "virtual/libx11 libsm libxfce4util libxfce4ui gtk+ libwnck dbus dbus-glib xfconf"
DEPENDS += "${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}"
RDEPENDS_${PN} = "netbase xinit dbus-x11 iceauth consolekit upower"
PR = "r6"

inherit xfce update-alternatives

SRC_URI[md5sum] = "4768e1a41a0287af6aad18b329a0f230"
SRC_URI[sha256sum] = "bb8aa9a74c3d382840596fb4875144d66c7f3f47c8e9ee81d31e3428a72c46ce"
SRC_URI += "file://0001-Handle-multiple-interactive-session-save-bug-5379.patch \
            file://0002-Remove-gnome-keyring-remains.patch \
            file://0003-configure.in-hard-code-path-to-iceauth.patch \
            file://0001-Add-shutdown-reboot-functionality-for-systemd-bug-87.patch \
"

ALTERNATIVE_${PN} = "x-session-manager"
ALTERNATIVE_TARGET[x-session-manager] = "${bindir}/xfce4-session"

# protect from frightening message that xfce might not work correctly
pkg_postinst_${PN} () {
    echo 127.0.0.1    ${MACHINE} >> /etc/hosts
}

FILES_${PN} += "${libdir}/xfce4/*/*/*.so \
                ${libdir}/xfce4/session/*-*-* \
                ${datadir}/xsessions \
                ${datadir}/themes/Default/balou/*"

FILES_${PN}-dbg += "${libdir}/xfce4/*/*/.debug"

FILES_${PN}-staticdev += "${libdir}/xfce4/*/*/*.*a"
