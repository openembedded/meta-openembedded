SUMMARY = "A general-purpose utility library"
DESCRIPTION = "GLib is a general-purpose utility library, which provides many useful data types, macros, type conversions, string utilities, file utilities, a main loop abstraction, and so on."
# pcre is under BSD;
# docs/reference/COPYING is with a 'public domai'-like license!
LICENSE = "LGPLv2+ & BSD & PD"
LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7 \
                    file://glib/glib.h;startline=4;endline=17;md5=a4332fe58b076f29d07c9c066d2967b6 \
                    file://gmodule/COPYING;md5=3bf50002aefd002f49e7bb854063f7e7 \
                    file://gmodule/gmodule.h;startline=4;endline=17;md5=76ab161b37202cd004073c42fac276ed \
                    file://glib/pcre/COPYING;md5=266ebc3ff74ee9ce6fad65577667c0f4 \
                    file://glib/pcre/pcre.h;startline=11;endline=35;md5=ce867cf87ebbd2bb55e980d90328390d \
                    file://docs/reference/COPYING;md5=f51a5100c17af6bae00735cd791e1fcc"
BUGTRACKER = "http://bugzilla.gnome.org"
SECTION = "libs"

require glib-2.0.inc

DEPENDS = "glib-2.0-native gtk-doc-native zip dbus"
DEPENDS_virtclass-native = "gtk-doc-native pkgconfig-native gettext-native dbus-native"
DEPENDS_virtclass-nativesdk = "libtool-nativesdk"
PACKAGES =+ "${PN}-utils "

LEAD_SONAME = "libglib-2.0.*"
FILES_${PN}-utils = "${bindir}/*"

inherit autotools pkgconfig gettext

S = "${WORKDIR}/glib-${PV}"

EXTRA_OECONF = "--disable-debug --enable-included-printf=no"
EXTRA_OECONF_virtclass-native = ""
EXTRA_OECONF_linuxstdbase = "--enable-included-printf=no"

FILES_${PN} = "${libdir}/lib*${SOLIBS} ${sysconfdir}/bash_completion.d ${datadir}/glib-2.0/schemas"
FILES_${PN}-dev += "${libdir}/glib-2.0/include"
FILES_${PN}-dbg += "${datadir}/glib-2.0/gdb ${datadir}/gdb"

ARM_INSTRUCTION_SET = "arm"
