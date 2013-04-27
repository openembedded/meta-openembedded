# nautilus OE build file
# Copyright (C) 2005, Advanced Micro Devices, Inc.  All Rights Reserved
# Released under the MIT license (see packages/COPYING)

LICENSE="GPLv2 & LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=f08a446809913fc9b3c718f0eaea0426 \
                    file://COPYING.LIB;md5=f30a9716ef3762e3467a2f62bf790f0a"

PR = "r4"

DEPENDS = "libnotify3 gtk+3 libunique gvfs librsvg libexif gnome-desktop3"
# optional: tracker

# needs libnotify3 which conflicts with libnotify, mixing them in build breaks couple of packages
EXCLUDE_FROM_WORLD = "1"

# to include nautilus3/no-try-run-strftime.diff before the rest
FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
BPN = "nautilus"
inherit gnome

SRC_URI += "file://no-try-run-strftime.diff"
SRC_URI[archive.md5sum] = "ecd100ed94431363fa68b217351649c0"
SRC_URI[archive.sha256sum] = "584e97d3eb093ee16a779809b679150a636bcd4a3767fb604993013b523d2df1"

EXTRA_OECONF = " --disable-gtk-doc  --disable-update-mimedb --enable-nst-extension"
export SYSROOT = "${STAGING_DIR_HOST}"

do_configure() {
    sed -i -e /docs/d Makefile.am
    autotools_do_configure
}

RDEPENDS_${PN} = "gvfs gvfsd-ftp gvfsd-sftp gvfsd-trash glib-2.0-utils"
FILES_${PN} += "${datadir}/icons \
                /usr/libexec/ \
                ${datadir}/nautilus* \
                ${datadir}/dbus-1 \
                ${libdir}/nautilus/extensions*/*.so \
"
FILES_${PN}-dbg += "/usr/libexec/.debug \
    ${libdir}/nautilus/extensions*/.debug"

# Don't make nautils drag us in
PRIVATE_LIBS = "libnautilus-extension.so.1"

pkg_postinst_${PN} () {
if [ -n "$D" ]; then
    exit 1
fi

glib-compile-schemas ${datadir}/glib-2.0/schemas
}
