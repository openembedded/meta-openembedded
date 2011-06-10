DESCRIPTION = "GNOME settings daemon"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

DEPENDS = "libnotify libxklavier gnome-doc-utils gtk+ libglade libgnomekbd gnome-desktop librsvg libxml2 libart-lgpl"

inherit gnome
SRC_URI[archive.md5sum] = "6420706542e8fb959acba7e2a69ee35f"
SRC_URI[archive.sha256sum] = "0074b3fec3ad6e3ab91a05dc20906b06101ea8bca0cd2caf394a5cc141b05e86"

EXTRA_OECONF = "--disable-esd --x-includes=${STAGING_INCDIR} --x-libraries=${STAGING_LIBDIR}"
ASNEEDED = ""

do_configure_prepend() {
	sed -i -e 's:-L$libdir::g' -e 's:-I$includedir::g' configure.ac
}

FILES_${PN} += "${libdir}/gnome-settings-daemon-2.0/*.so ${libdir}/gnome-settings-daemon-2.0/*plugin \
                ${datadir}/dbus-1/ \
                ${datadir}/icon* \
                ${datadir}/gnome-control-center \
                ${datadir}/xsession*"

FILES_${PN}-dbg += "${libdir}/gnome-settings-daemon-2.0/.debug"
FILES_${PN}-dev += "${libdir}/gnome-settings-daemon-2.0/*.a ${libdir}/gnome-settings-daemon-2.0/*.la"
