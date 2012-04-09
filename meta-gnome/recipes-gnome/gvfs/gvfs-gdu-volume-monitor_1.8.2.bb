require gvfs.inc

BPN = "gvfs"

DEPENDS = "gvfs gnome-disk-utility"

PR = "${INC_PR}.1"

# we need gdu files only: reduce files to delete in libexecdir
EXTRA_OECONF += "--disable-gphoto2"

do_install_append() {
	rm -rf ${D}${sysconfdir}
	rm -rf ${D}${bindir}
	rm -rf ${D}${includedir}
	rm -rf ${D}${libdir}
	rm -rf ${D}${datadir}/gvfs/mounts
	rm -rf ${D}${datadir}/glib-2.0
	rm -rf ${D}${datadir}/GConf
	rm -f ${D}${datadir}/dbus-1/services/gvfs-*
	rm -f ${D}${libexecdir}/gvfsd*
	rm -f ${D}${libexecdir}/gvfs-fuse-daemon
	rm -f ${D}${libexecdir}/gvfs-gphoto2-volume-monitor
}
