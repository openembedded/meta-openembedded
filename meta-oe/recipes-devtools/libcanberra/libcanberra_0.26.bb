DESCRIPTION = "Libcanberra is an implementation of the XDG Sound Theme and Name \
Specifications, for generating event sounds on free desktops."
LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://LGPL;md5=2d5025d4aa3495befef8f17206a5b0a1"
DEPENDS = "alsa-lib gstreamer gtk+ libtool libvorbis gconf"
SECTION = "libs/multimedia"
AUTHOR = "Lennart Poettering"
HOMEPAGE = "http://0pointer.de/lennart/projects/libcanberra"
PR = "r1"

inherit autotools vala

SRC_URI = "http://0pointer.de/lennart/projects/libcanberra/libcanberra-${PV}.tar.gz \
           file://libcanberra-increase-buffer-size.patch"

SRC_URI[md5sum] = "ee2c66ada7c851a4e7b6eb1682285a24"
SRC_URI[sha256sum] = "4b5d8d2c2835133620adbc53745dd107b6e58b9a2963059e8f457143fee00982"

EXTRA_OECONF = "\
  --enable-alsa \
  --enable-gstreamer \
  --enable-gtk \
  --enable-multi \
  --enable-null \
  --disable-oss \
  --disable-pulse \
  --disable-tdb \
"
# enable pulse again when pulseaudio >= 0.9.11 is the default in OE

python populate_packages_prepend() {
	plugindir = bb.data.expand('${libdir}/${P}/', d)
	do_split_packages(d, plugindir, '^libcanberra-(.*)\.so$', 'libcanberra-%s', '%s support library', extra_depends='' )
}

PACKAGES =+ "${PN}-gtk"

PACKAGES_DYNAMIC = "libcanberra-*"

FILES_${PN}-gtk = "\
  ${sysconfdir}/gconf \
  ${bindir}/* \
  ${libdir}/libcanberra-gtk.so.* \
  ${libdir}/gtk-2.0/modules/* \
  ${datadir}/gnome \
  ${datadir}/gdm \
"

FILES_${PN}-dev += "\
  ${libdir}/${P}/*.la \
"

FILES_${PN}-dbg += "\
  ${libdir}/gtk-2.0/modules/.debug \
  ${libdir}/${P}/.debug \
"
