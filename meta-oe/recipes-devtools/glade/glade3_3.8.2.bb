DESCRIPTION = "Glade - A User Interface Designer"
HOMEPAGE = "http://www.gnu.org/software/gnash"
LICENSE = "GPLv2 & LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=aabe87591cb8ae0f3c68be6977bb5522 \
                    file://COPYING.LGPL;md5=3bf50002aefd002f49e7bb854063f7e7"
DEPENDS = "gtk+ gnome-doc-utils-native"

PR = "r1"

inherit autotools pkgconfig pythonnative

SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/glade3/3.8/glade3-${PV}.tar.xz \
           file://0001-gnome-doc-utils.make-sysrooted-pkg-config.patch"
SRC_URI[md5sum] = "b5da0ce717b2dc710e93a10b97c0f9ce"
SRC_URI[sha256sum] = "f180a5018eee6e3fe574854cb025af897dd9962b01d17d5752e626876d388b19"

EXTRA_OECONF += "--disable-scrollkeeper"

do_configure_prepend() {
    sed -i '/^if HAVE_GNOME_DOC_UTILS/,/^endif/d' ${S}/Makefile.am
}

FILES_${PN} += "${datadir}/icons"
FILES_${PN}-dbg += "${libdir}/glade3/modules/.debug"
FILES_${PN}-dev += "${libdir}/glade3/modules/*.la"
