SUMMARY = "GNOME Structured File Library"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=61464cfe342798eeced82efe9ae55f63"

SECTION = "libs"

DEPENDS = "libxml2 bzip2 glib-2.0 zlib"

GNOMEBASEBUILDCLASS = "autotools"
inherit gnomebase gobject-introspection gettext gtk-doc

SRC_URI[archive.sha256sum] = "d18869264a2513cfb071712486d115dada064ff8a040265b49936bca06f17623"
SRC_URI += " file://0001-configure.ac-drop-a-copy-paste-of-introspection.m4-m.patch"

PACKAGECONFIG ??= ""
PACKAGECONFIG[gdk-pixbuf] = "--with-gdk-pixbuf,--without-gdk-pixbuf,gdk-pixbuf"

EXTRA_OECONF = "\
    --with-bz2 \
"

FILES:${PN} += "${datadir}/thumbnailers"
