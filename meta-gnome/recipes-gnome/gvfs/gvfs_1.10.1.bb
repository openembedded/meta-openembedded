require gvfs.inc

DEPENDS = "libsoup-2.4 libgnome-keyring glib-2.0 avahi gconf intltool-native libgudev"
# optional: obexftp libimobiledevice

PR = "${INC_PR}.0"

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

PACKAGECONFIG ?= "libgphoto2"

PACKAGECONFIG[libgphoto2] = "--enable-gphoto2, --disable-gphoto2, libgphoto2"
PACKAGECONFIG[samba] = "--enable-samba --with-samba-includes=${STAGING_INCDIR} \
                        --with-samba-libs=${STAGING_LIBDIR}, --disable-samba, samba"
# needs meta-filesystems
PACKAGECONFIG[fuse] = "--enable-fuse, --disable-fuse, fuse"
PACKAGECONFIG[archive] = "--enable-archive, --disable-archive, libarchive"
PACKAGECONFIG[obexftp] = "--enable-obexftp, --disable-obexftp, bluez4 expat"
# libcdio-paranoia recipe doesn't exist yet
PACKAGECONFIG[cdda] = "--enable-cdda, --disable-cdda, libcdio-paranoia"
