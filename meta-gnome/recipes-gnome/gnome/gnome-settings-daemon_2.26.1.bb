DESCRIPTION = "GNOME settings daemon"
LICENSE = "GPL"
DEPENDS = "libxklavier gnome-doc-utils gtk+ libglade libgnomekbd gnome-desktop librsvg libxml2 libart-lgpl"

PR = "r3"

inherit gnome

SRC_URI += "file://desktop-moblin.patch;patch=1 \
            file://gnome-settings-daemon-fix-gthread.patch;patch=1 \
            file://mount-plugin.patch;patch=1 \
            file://gnome-settings-daemon-2.24.0-catch-deviceadded.patch;patch=1 \
            file://configurefix.patch;patch=1 \
            file://mojito-cleanup.patch;patch=1"

EXTRA_OECONF = "--disable-esd --x-includes=${STAGING_INCDIR} --x-libraries=${STAGING_LIBDIR}"
ASNEEDED = ""

FILES_${PN} += "${libdir}/gnome-settings-daemon-2.0/*.so ${libdir}/gnome-settings-daemon-2.0/*plugin \
                ${datadir}/dbus-1/ \
                ${datadir}/icon* \
                ${datadir}/xsession*"

FILES_${PN}-dbg += "${libdir}/gnome-settings-daemon-2.0/.debug"
FILES_${PN}-dev += "${libdir}/gnome-settings-daemon-2.0/*.a ${libdir}/gnome-settings-daemon-2.0/*.la"
