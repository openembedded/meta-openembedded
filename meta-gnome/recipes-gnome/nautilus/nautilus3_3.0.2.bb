# nautilus OE build file
# Copyright (C) 2005, Advanced Micro Devices, Inc.  All Rights Reserved
# Released under the MIT license (see packages/COPYING)

LICENSE="GPLv2 & LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=f08a446809913fc9b3c718f0eaea0426 \
                    file://COPYING.LIB;md5=f30a9716ef3762e3467a2f62bf790f0a"

PR = "r2"

DEPENDS = "libnotify3 gtk+3 libunique gvfs librsvg libexif gnome-desktop3"
# optional: tracker

BPN = "nautilus"
inherit gnome

SRC_URI += "file://no-try-run-strftime.diff"
SRC_URI[archive.md5sum] = "949187b1f241137ad3a5e6bdca3dfb0b"
SRC_URI[archive.sha256sum] = "6a4c6b75593f0e815763d0040878cd908da72318f18b05f33d24040e0d602b22"

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
FILES_${PN}-dbg += "/usr/libexec/.debug"

# Don't make nautils drag us in
PRIVATE_LIBS = "libnautilus-extension.so.1"

pkg_postinst_${PN} () {
if [ -n "$D" ]; then
    exit 1
fi

glib-compile-schemas ${datadir}/glib-2.0/schemas
}
