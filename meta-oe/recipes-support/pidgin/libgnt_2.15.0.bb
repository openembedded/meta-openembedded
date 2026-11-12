SUMMARY = "GNT: The GLib Ncurses Toolkit"

SECTION = "libs"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=c9a1abacd0166f595a9fbe6afb1f0d5e"
DEPENDS = "glib-2.0 ncurses libxml2 glib-2.0-native"

inherit meson pkgconfig

# SRCREV = "0a44b1d01c41"
# SRC_URI = "hg://keep.imfreedom.org/${BPN};module=${BPN}

SRC_URI = "${SOURCEFORGE_MIRROR}/project/pidgin/${BPN}/${PV}/${BP}.tar.xz \
"
SRC_URI[sha256sum] = "6dce3d4f2fb33c242d8223ddda5f1042f3dfb0ad7c395d4e33a73f92423a58a3"

UPSTREAM_CHECK_URI = "https://sourceforge.net/projects/pidgin/files/libgnt/"
UPSTREAM_CHECK_REGEX = "${BPN}/(?P<pver>\d+(\.\d+)+)"

EXTRA_OEMESON = "-Ddoc=false"

FILES:${PN} += "${libdir}/gnt/s.so ${libdir}/gnt/irssi.so"
