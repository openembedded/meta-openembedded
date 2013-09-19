SUMMARY = "GPS Multiplexing Daemon"
DESCRIPTION = "Gypsy is a GPS multiplexing daemon which allows \
multiple clients to access GPS data from multiple GPS sources \
concurrently.  Gypsy also hides the details of parsing NMEA from the \
client applications, passing the data as simple values for the clients \
to use."
LICENSE = "GPLv2.0 & LGPLv2.1"
SECTION = "console/network"
DEPENDS = "glib-2.0 dbus bluez4 dbus-glib libxslt-native libxslt"

PACKAGES += "libgypsy"
SRC_URI = "file://gypsy-initscript"

inherit autotools pkgconfig gtk-doc update-rc.d

INITSCRIPT_NAME = "gypsy-daemon"
# make sure it starts after X
INITSCRIPT_PARAMS = "start 10 5 3 2 . stop 20 0 1 6 ."

EXTRA_OECONF += "--with-distro=debian"

FILES_${PN} = " \
  ${sysconfdir}/gypsy.conf \
  ${sysconfdir}/init.d/gypsy-daemon \
  ${sysconfdir}/dbus-1 \
  ${libexecdir}/gypsy-daemon \
  ${datadir}/dbus-1 \
"

FILES_libgypsy = " \
  ${libdir}/libgypsy${SOLIBS} \
"

do_install_append() {
  install -D ${WORKDIR}/gypsy-initscript ${D}/${sysconfdir}/init.d/gypsy-daemon
}
