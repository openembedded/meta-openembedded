DESCRIPTION = "GNOME disk utility"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=e9115d11797a5e6b746b4e9b90194564"

DEPENDS = "glib-2.0 gtk+ libnotify libunique udisks avahi-ui virtual/libx11 libatasmart nautilus"

PR = "r1"

inherit gnome
SRC_URI[archive.md5sum] = "f0366c8baebca0404d190b2d78f3582d"
SRC_URI[archive.sha256sum] = "03e461b6bda7f773f8018d25fa3213d3073d4dc83a76e6b39d962652f4de6a98"

SRC_URI += "\
     file://disable-scrollkeeper.patch \
     file://fix-dbus-interfaces.patch \
     file://sysrooted-pkg-config.patch \
     "

EXTRA_OECONF += "--disable-scrollkeeper"

PACKAGES =+ "${PN}-nautilus-extension"
FILES_${PN}-nautilus-extension += "${libdir}/nautilus/extensions-2.0/*.so"
