DESCRIPTION = "GNOME Structured File Library"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=dc7371b50816c96e145fa0f8ade8e24d \
                    file://COPYING.LIB;md5=61464cfe342798eeced82efe9ae55f63"

SECTION = "libs"

DEPENDS= "libxml2 bzip2 glib-2.0 zlib"

inherit autotools pkgconfig gnome gconf

SRC_URI[archive.md5sum] = "3c5a4fbd16a727c36974078e6d0e9575"
SRC_URI[archive.sha256sum] = "8919e725aadd785380350c8dec7427d82cf33164bc9a9a549df9440a0c3da6d5"

GNOME_COMPRESS_TYPE = "xz"

EXTRA_OECONF = "\
    --without-python \
    --without-gnome-vfs \
    --without-bonobo \
    --disable-gtk-doc \
    --with-bz2 \
"

RDEPENDS_${PN} = "gconf" 

FILES_${PN} += "${datadir}/thumbnailers"
