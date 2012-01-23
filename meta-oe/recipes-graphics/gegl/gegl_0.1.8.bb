DESCRIPTION = "GEGL (Generic Graphics Library) is a graph based image processing framework."
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
DEPENDS = "babl librsvg glib-2.0 gtk+ pango cairo expat zlib libpng jpeg virtual/libsdl"

inherit gnome

EXTRA_OECONF = "--disable-docs "

SRC_URI = "ftp://ftp.gimp.org/pub/${PN}/0.1/${PN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "c8279b86b3d584ee4f503839fc500425"
SRC_URI[sha256sum] = "106b9574b6d5fb282683794f7a7f090a1a3f4a388890e592b202827a4ca76f75"

FILES_${PN} += "${libdir}/gegl-*/*.so"
FILES_${PN}-dev += "${libdir}/gegl-*/*.la"
FILES_${PN}-dbg += "${libdir}/gegl-*/.debug"
