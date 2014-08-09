SUMMARY = "GNOME default webbrowser"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "gnome-doc-utils libsoup-2.4 webkit-gtk gtk+ gconf iso-codes startup-notification ca-certificates libgnome-keyring avahi libnotify"

inherit gnome
SRC_URI[archive.md5sum] = "0c566b3ffd428d2135e3c8cb65352d64"
SRC_URI[archive.sha256sum] = "278a5c00ce07e6a3ea440d289de22dbec3ebec4ded4ff3b4c48b580f469c2dcc"

EXTRA_OECONF += " --disable-nss --with-distributor-name=${DISTRO} --without-ca-file"

do_configure_prepend() {
    touch ${S}/gnome-doc-utils.make
    sed -i -e s:help::g ${S}/Makefile.am
}

FILES_${PN} += "${datadir}/icons ${datadir}/dbus-1"
RDEPENDS_${PN} = "iso-codes"
RRECOMMENDS_${PN} = "ca-certificates"

