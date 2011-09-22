DESCRIPTION = "default icon theme that all icon themes automatically inherit from."
HOMEPAGE = "http://icon-theme.freedesktop.org/wiki/HicolorTheme"
BUGTRACKER = "https://bugs.freedesktop.org/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=f08a446809913fc9b3c718f0eaea0426"

SECTION = "unknown"
inherit gnome allarch

PR = "r1"

SRC_URI = "http://icon-theme.freedesktop.org/releases/${BPN}-${PV}.tar.gz \
        file://index.theme"

FILES_${PN} += "${datadir}/icons"

do_install_append () {
	install -m 0644 ${WORKDIR}/index.theme ${D}/${datadir}/icons/hicolor
}
