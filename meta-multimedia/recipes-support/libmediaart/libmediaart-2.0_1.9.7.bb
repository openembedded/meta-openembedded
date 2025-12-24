SUMMARY = "Library tasked with managing, extracting and handling media art caches"

LICENSE = "LGPL-2.0-or-later & GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=4fbd65380cdd255951079008b364516c \
                    file://libmediaart/extract.c;endline=18;md5=dff2b6328ab067b5baadc135f9876c36 \
                    file://tests/mediaarttest.c;endline=18;md5=067106eaa1f7a9d918759a096667f18e"

DEPENDS = "glib-2.0 gdk-pixbuf"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gobject-introspection vala features_check

SRC_URI = "${GNOME_MIRROR}/libmediaart/1.9/libmediaart-${PV}.tar.xz"
SRC_URI[sha256sum] = "2b43dd9f54f0d8d0b89e2addb83341ab06d7b98cb1b2e704383584af9c560f6b"

S = "${WORKDIR}/libmediaart-${PV}"

# gobject-introspection is mandatory and cannot be configured
REQUIRED_DISTRO_FEATURES = "gobject-introspection-data"
GIR_MESON_OPTION = ""

EXTRA_OEMESON = "-Dimage_library=gdk-pixbuf"
