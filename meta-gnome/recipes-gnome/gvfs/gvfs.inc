DESCRIPTION = "gvfs is a userspace virtual filesystem"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7"

inherit gnome

INC_PR = "r0"

GNOME_COMPRESS_TYPE = "xz"

SRC_URI[archive.md5sum] = "0c968704be003d2f6eab29388c41664e"
SRC_URI[archive.sha256sum] = "cd30facee5ad6cd0fafc5337bbf7c06a0bff3936f9ff2ae31519b301efcc9288"
SRC_URI += " \
    file://0001-Revert-Respect-UDISKS_AUTOMOUNT_HINT-available-in-re.patch \
    file://0002-Adapt-to-GLib-2.31-deprecations-and-thread-API-chang.patch \
    file://0003-Adapt-to-glib-mutex-API-changes.patch \
    file://0004-Adapt-to-glib-thread-API-changes.patch \
    file://0005-client-gdaemonfile.c-replace-g_memmove-by-memmove.patch \
"

EXTRA_OECONF = "--with-archive-includes=${STAGING_INCDIR} \
                --with-archive-libs=${STAGING_LIBDIR} \
"

FILES_${PN} += "${datadir}/dbus-1/services/*"

