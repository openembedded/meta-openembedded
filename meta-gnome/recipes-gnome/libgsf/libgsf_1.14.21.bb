DESCRIPTION = "GNOME Structured File Library"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=dc7371b50816c96e145fa0f8ade8e24d \
                    file://COPYING.LIB;md5=61464cfe342798eeced82efe9ae55f63"

PR = "r3"

SECTION = "libs"

DEPENDS= "libxml2 bzip2 glib-2.0 zlib"

inherit autotools pkgconfig gnome gconf

SRC_URI[archive.md5sum] = "2b702648b853402554c97d75405c60d3"
SRC_URI[archive.sha256sum] = "eef0a9d6eca4e6af6c16b208947e3c958c428b94d22792bdd0b80c08a4b301db"

EXTRA_OECONF = "\
		--without-python \
		--without-gnome-vfs --without-bonobo \
		--disable-gtk-doc \
		--with-bz2"

RDEPENDS_${PN} = "gconf" 
