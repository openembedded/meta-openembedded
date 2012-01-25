DESCRIPTION = "The GIMP is the GNU Image Manipulation Program."
HOMEPAGE = "http://www.gimp.org"
SECTION = "x11/graphics"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=878e3965c7b52d85827c75f5a2f3b314"

DEPENDS = "babl gdk-pixbuf-native libart-lgpl gtk+ jpeg libpng libexif tiff webkit-gtk lcms gegl poppler"

PR = "r1"

inherit gnome

SRC_URI = "ftp://ftp.gimp.org/pub/gimp/v2.6/gimp-${PV}.tar.bz2 \
           file://gimp-2.6.11-poppler18.patch"
SRC_URI[md5sum] = "bb2939fe13e54fc7255cef5d097bb5dd"
SRC_URI[sha256sum] = "9b6d08d0803b3912ea596d1b77b9c21ee13778c23388a225c004b8c1587cb0a1"

EXTRA_OECONF = "--disable-python \
                --without-wmf"

do_configure_append() {
	find ${S} -name Makefile | xargs sed -i s:'-I$(includedir)':'-I.':g
	find ${S} -name Makefile | xargs sed -i s:'-I/usr/include':'-I${STAGING_INCDIR}':g
}

FILES_${PN}-dbg += "${libdir}/gimp/2.0/*/.debug"
