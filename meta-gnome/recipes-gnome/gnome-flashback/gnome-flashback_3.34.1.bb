SUMMARY = "GNOME Flashback (GNOME 2) session"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

inherit gnomebase gsettings gtk-icon-cache gettext upstream-version-is-even features_check

REQUIRED_DISTRO_FEATURES = "x11 polkit systemd pam"

DEPENDS += " \
    upower \
    ibus \
    libxkbfile \
    polkit \
    metacity \
    gnome-desktop3 \
    gnome-bluetooth \
"

SRC_URI[archive.md5sum] = "e328a51b69d97e291709458dfd8c0a1a"
SRC_URI[archive.sha256sum] = "ddba0c9661ad93b58ccbde267f6ecbc02da2c15f9d38d87d5504f4670c2fbc77"

do_install_append() {
    # no oe-layer has compiz -> remove dead session
    rm -f ${D}${datadir}/xsessions/gnome-flashback-compiz.desktop
}

FILES_${PN} += " \
    ${datadir}/xsessions \
    ${datadir}/desktop-directories \
    ${datadir}/gnome-session \
    ${systemd_user_unitdir} \
"

RDEPENDS_${PN} += "metacity gnome-panel"
