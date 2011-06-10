# nautilus OE build file
# Copyright (C) 2005, Advanced Micro Devices, Inc.  All Rights Reserved
# Released under the MIT license (see packages/COPYING)

LICENSE="GPLv2 && LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=7c0048536e43642a1f3a724c2909872b \
                    file://COPYING.LIB;md5=f30a9716ef3762e3467a2f62bf790f0a"

DEPENDS = "libunique gvfs librsvg libexif esound gnome-desktop orbit2-native"
# optional: tracker

inherit gnome

SRC_URI[archive.md5sum] = "51565aa10d1625dff56e381228346911"
SRC_URI[archive.sha256sum] = "2d4ff28c7a7aa5d40eb2468149954a564c257a305183773057584d22d15347a2"

SRC_URI += "file://idl-sysroot.patch \
            file://no-try-run-strftime.diff \
"


EXTRA_OECONF = " --disable-gtk-doc  --disable-update-mimedb "
export SYSROOT = "${STAGING_DIR_HOST}"

do_configure() {
	sed -i -e /docs/d Makefile.am
	autotools_do_configure
	# We need native orbit-idl with target idl files. No way to say it in a clean way:
	find -name Makefile -exec sed -i '/\/usr\/bin\/orbit-idl-2/{s:/usr/bin:${STAGING_BINDIR_NATIVE}:;s:/usr/share:${STAGING_DATADIR}:g}' {} \;
}

PACKAGES += " libnautilus"

RDEPENDS_${PN} = "gvfs gvfsd-ftp gvfsd-sftp gvfsd-trash"
FILES_${PN} += "${datadir}/icons  /usr/libexec/ "
FILES_libnautilus = "/usr/lib/*.so*"
FILES_${PN}-dbg += "/usr/libexec/.debug"


