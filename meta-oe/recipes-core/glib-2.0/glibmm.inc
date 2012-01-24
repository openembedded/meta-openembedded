DESCRIPTION = "C++ bindings for the glib library."
HOMEPAGE = "http://www.gtkmm.org/"
SECTION = "libs"

LICENSE = "LGPLv2.1 & GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d8045f3b8f929c1cb29a1e3fd737b499 \
                    file://COPYING.tools;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "mm-common glib-2.0 libsigc++-2.0"
SHRT_VER = "${@bb.data.getVar('PV',d,1).split('.')[0]}.${@bb.data.getVar('PV',d,1).split('.')[1]}"

SRC_URI = "ftp://ftp.gnome.org/pub/GNOME/sources/glibmm/${SHRT_VER}/glibmm-${PV}.tar.bz2;name=archive"

inherit autotools pkgconfig

do_install_append() {
	install -d ${D}${datadir}/glibmm-2.4
	install -d ${D}${datadir}/aclocal

	install -m 0644 glib/glibmmconfig.h ${D}${datadir}/glibmm-2.4/
	install -m 0644 scripts/glibmm_check_perl.m4 ${D}${datadir}/aclocal/ || true
}

FILES_${PN} = "${libdir}/lib*.so.*"
FILES_${PN}-dev += "${datadir}/glibmm-* ${libdir}/glibmm-2.4/include/ ${libdir}/glibmm-2.4/proc/ ${libdir}/giomm-2.4/include/"

LIBV = "2.4.0"

