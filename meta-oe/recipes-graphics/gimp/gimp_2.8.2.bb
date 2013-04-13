DESCRIPTION = "The GIMP is the GNU Image Manipulation Program."
HOMEPAGE = "http://www.gimp.org"
SECTION = "x11/graphics"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "babl gdk-pixbuf-native libart-lgpl gtk+ jpeg libpng libexif tiff webkit-gtk lcms gegl poppler"

inherit gnome

SRC_URI = "ftp://ftp.gimp.org/pub/gimp/v2.8/gimp-${PV}.tar.bz2"
SRC_URI[md5sum] = "b542138820ca3a41cbd63fc331907955"
SRC_URI[sha256sum] = "0cd1a7e67e132ead810e16e31ff929394c83fcf841e4a295c45d6f3829601ad9"

EXTRA_OECONF = "--disable-python \
                --without-wmf"

do_configure_append() {
    find ${S} -name Makefile | xargs sed -i s:'-I$(includedir)':'-I.':g
    find ${S} -name Makefile | xargs sed -i s:'-I/usr/include':'-I${STAGING_INCDIR}':g
}

FILES_${PN}-dbg += "${libdir}/gimp/2.0/*/.debug"
