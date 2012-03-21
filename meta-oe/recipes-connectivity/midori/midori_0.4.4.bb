DESCRIPTION = "Midori is a lightweight web browser."
HOMEPAGE = "http://www.twotoasts.de/index.php?/pages/midori_summary.html"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"
DEPENDS = "webkit-gtk libsoup-2.4 libsexy openssl vala-native python-native python-docutils-native librsvg-native"

PR = "r1"

SRC_URI = "http://archive.xfce.org/src/apps/midori/0.4/midori-${PV}.tar.bz2;name=midori \
          "
SRC_URI[midori.md5sum] = "a6578ebfd237c0f22cce49113b95f70c"
SRC_URI[midori.sha256sum] = "fadd43f76c1c9f6a16483e60a804e58fb6817c6a595b1acdd59bcbdd7b35bca2"

inherit gtk-icon-cache pkgconfig

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
