SUMMARY = "C++ bindings for the glib library"
HOMEPAGE = "http://www.gtkmm.org/"
SECTION = "libs"
LICENSE = "LGPLv2.1 & GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=42dfffebc56fec7527aac53b7a89d1d8 \
                    file://COPYING.tools;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "mm-common glib-2.0 libsigc++-2.0 glib-2.0-native"
inherit autotools pkgconfig

SHRT_VER = "${@d.getVar('PV').split('.')[0]}.${@d.getVar('PV').split('.')[1]}"

SRC_URI = " \
    ftp://ftp.gnome.org/pub/GNOME/sources/glibmm/${SHRT_VER}/glibmm-${PV}.tar.xz \
    file://remove-examples.patch \
"
SRC_URI[md5sum] = "4a0402ac65a7c47424b4e21b81b079c0"
SRC_URI[sha256sum] = "d34189237b99e88228e6f557f7d6e62f767fe356f395a244f5ad0e486254b645"

do_install_append() {
    install -d ${D}${datadir}/glibmm-2.4
    install -d ${D}${datadir}/aclocal

    install -m 0644 glib/glibmmconfig.h ${D}${datadir}/glibmm-2.4/
    install -m 0644 scripts/glibmm_check_perl.m4 ${D}${datadir}/aclocal/ || true

    for i in generate_wrap_init.pl gmmproc; do
        sed -i -e '1s,.*,#!${bindir}/env perl,' ${D}${libdir}/glibmm-2.4/proc/$i
    done
}

FILES_${PN} = "${libdir}/lib*.so.*"
FILES_${PN}-dev += "${datadir}/glibmm-* ${libdir}/glibmm-2.4/include/ ${libdir}/glibmm-2.4/proc/ ${libdir}/giomm-2.4/include/"

RDEPENDS_${PN}-dev = "perl"
SECURITY_CFLAGS = "${SECURITY_NO_PIE_CFLAGS}"
