SUMMARY = "The GIMP is the GNU Image Manipulation Program"
HOMEPAGE = "http://www.gimp.org"
SECTION = "x11/graphics"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "babl gdk-pixbuf-native libart-lgpl gtk+ jpeg libpng libexif tiff lcms gegl poppler jasper bzip2"
DEPENDS += "${@base_contains('DISTRO_FEATURES', 'x11', 'libxmu libxpm', '', d)}"

inherit gnome

PACKAGECONFIG ??= ""
PACKAGECONFIG[helpbrowser] = "--with-webkit, --without-webkit, webkit-gtk"

SRC_URI = "http://ftp.gimp.org/pub/gimp/v2.8/gimp-${PV}.tar.bz2 \
           file://freetype.patch \
          "
SRC_URI[md5sum] = "84c964aab7044489af69f7319bb59b47"
SRC_URI[sha256sum] = "e7fd8b19f989138d826003c75f56bd5b6f136eef597e86e3978ede0bba470ae6"

EXTRA_OECONF = "--disable-python \
                --without-wmf"

do_configure_append() {
    find ${B} -name Makefile | xargs sed -i s:'-I$(includedir)':'-I.':g
    find ${B} -name Makefile | xargs sed -i s:'-I/usr/include':'-I${STAGING_INCDIR}':g
}

FILES_${PN}-dbg += "${libdir}/gimp/2.0/*/.debug"
FILES_${PN}  += "${datadir}/appdata"
