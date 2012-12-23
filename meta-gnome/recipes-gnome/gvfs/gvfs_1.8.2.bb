require gvfs.inc

DEPENDS = "samba libsoup-2.4 gnome-keyring glib-2.0 fuse avahi fuse gconf libgphoto2"
# optional: obexftp libcdio libimobiledevice 

PR = "${INC_PR}.1"

# in case of gnome-disk-utility was alresdy built: avoid double files
# afc is enabled when it detects libimobiledevice
EXTRA_OECONF += "--disable-gdu --disable-afc"

PACKAGES =+ "gvfsd-ftp gvfsd-sftp gvfsd-trash"

FILES_${PN} += "${datadir}/glib-2.0 ${datadir}/GConf ${libdir}/gio/modules/*.so"
FILES_${PN}-dbg += "${libdir}/gio/modules/.debug/*"
FILES_${PN}-dev += "${libdir}/gio/modules/*.la"

FILES_gvfsd-ftp = "${libexecdir}/gvfsd-ftp ${datadir}/gvfs/mounts/ftp.mount"
FILES_gvfsd-sftp = "${libexecdir}/gvfsd-sftp ${datadir}/gvfs/mounts/sftp.mount"
FILES_gvfsd-trash = "${libexecdir}/gvfsd-trash ${datadir}/gvfs/mounts/trash.mount"

RDEPENDS_${PN} = "gvfs-gdu-volume-monitor"
RRECOMMENDS_gvfsd-ftp += "openssh-sftp openssh-ssh"
