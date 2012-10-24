DESCRIPTION = "Midori is a lightweight web browser."
HOMEPAGE = "http://www.twotoasts.de/index.php?/pages/midori_summary.html"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"
DEPENDS = "webkit-gtk libsoup-2.4 libsexy openssl python-native python-docutils-native librsvg-native libnotify"

PR = "r2"

SRC_URI = "http://archive.xfce.org/src/apps/midori/0.4/midori-${PV}.tar.bz2;name=midori \
          "
SRC_URI[midori.md5sum] = "3c3f0030611d9126ab90be48d44cab57"
SRC_URI[midori.sha256sum] = "910f47bd2b5f2998cd4fce45c08f9e282318820b68562d0bade2096b9a17e3ea"

inherit gtk-icon-cache pkgconfig vala

do_configure() {
    sed -i -e 's:, shell=False::g' -e s:/usr/X11R6/include::g -e s:/usr/X11R6/lib::g wscript
    ./configure \
            --prefix=${prefix} \
            --bindir=${bindir} \
            --sbindir=${sbindir} \
            --libexecdir=${libexecdir} \
            --datadir=${datadir} \
            --sysconfdir=${sysconfdir} \
            --sharedstatedir=${sharedstatedir} \
            --localstatedir=${localstatedir} \
            --libdir=${libdir} \
            --includedir=${includedir} \
            --infodir=${infodir} \
            --mandir=${mandir} \
            --disable-hildon \
            --disable-gtk3 \
}

PARALLEL_MAKE = ""
LDFLAGS_append = " -ljavascriptcoregtk-1.0 "
TARGET_CC_ARCH += "${LDFLAGS}"

do_install() {
    oe_runmake DESTDIR=${D} install
}

RRECOMMENDS_${PN} += "glib-networking ca-certificates gnome-icon-theme"

FILES_${PN}-dev += "${datadir}/vala/vapi"
