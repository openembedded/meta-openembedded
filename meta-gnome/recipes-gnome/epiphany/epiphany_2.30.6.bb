DESCRIPTION = "GNOME default webbrowser"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "libsoup-2.4 webkit-gtk gtk+ gconf iso-codes startup-notification ca-certificates"

inherit gnome

EXTRA_OECONF += " --disable-nss --with-distributor-name=${DISTRO} --without-ca-file"

do_configure_prepend() {
        touch ${S}/gnome-doc-utils.make
        sed -i -e s:help::g Makefile.am
}

FILES_${PN} += "${datadir}/icons ${datadir}/dbus-1"
RDEPENDS_${PN} = "iso-codes"
RRECOMMENDS_${PN} = "ca-certificates"

