# NOTE: WIP! This recipe does not cross-compile atm., only -native
SECTION = "libs"
DEPENDS = "glib-2.0 libffi bison-native"
BBCLASSEXTEND = "native"
PR = "r1"

LICENSE = "GPLv2+ & LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=90d577535a3898e1ae5dbf0ae3509a8c \
                    file://COPYING.GPL;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://COPYING.LGPL;md5=3bf50002aefd002f49e7bb854063f7e7"

SRC_URI[md5sum] = "e5cd63d6bcc5c105e898e7c33cf42175"
SRC_URI[sha256sum] = "4bf244db75df04499dea704e7734376c0fc5a3a17fb59be2123c8d76111e6fb8"

SRC_URI = "\
  ${GNOME_MIRROR}/gobject-introspection/0.9/${BPN}-${PV}.tar.bz2 \
  file://use-usr-bin-env-for-python.patch \
"
S = "${WORKDIR}/${BPN}-${PV}"

inherit autotools

do_configure_prepend() {
	touch -f gtk-doc.make
}

EXTRA_OECONF = "\
  --disable-gtk-doc \
  --disable-gtk-doc-html \
  --disable-gtk-doc-pdf \
  --disable-tests \
"
