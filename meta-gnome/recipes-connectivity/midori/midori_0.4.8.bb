DESCRIPTION = "Midori is a lightweight web browser."
HOMEPAGE = "http://www.twotoasts.de/index.php?/pages/midori_summary.html"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"
DEPENDS = "webkit-gtk libsoup-2.4 openssl python-native python-docutils-native librsvg-native libnotify libunique"

SRC_URI = "http://archive.xfce.org/src/apps/midori/0.4/midori-${PV}.tar.bz2;name=midori \
          "
SRC_URI[midori.md5sum] = "ce1b01fa5aa33d2ee50196df0c71e96f"
SRC_URI[midori.sha256sum] = "40bf23f84a3537f54d7eb45df1da40ebef54c321b1b34d0d0c8e45bb0cce5e9f"

inherit gtk-icon-cache pkgconfig vala pythonnative

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
            --disable-gtk3 \
            --disable-zeitgeist \
}

PARALLEL_MAKE = ""
TARGET_CC_ARCH += "${LDFLAGS}"

do_install() {
    oe_runmake DESTDIR=${D} install
}

RRECOMMENDS_${PN} += "glib-networking ca-certificates gnome-icon-theme"

FILES_${PN}-dev += "${datadir}/vala/vapi"
