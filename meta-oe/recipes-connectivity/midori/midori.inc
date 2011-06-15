DESCRIPTION = "Midori is a lightweight web browser."
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

DEPENDS = "webkit-gtk libsoup-2.4 libsexy openssl vala-native python-native python-docutils-native"

inherit gtk-icon-cache pkgconfig

SRC_URI = "http://archive.xfce.org/src/apps/midori/0.2/midori-${PV}.tar.bz2;name=midori \
"

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
}

do_install() {
	oe_runmake DESTDIR=${D} install
}

TARGET_CC_ARCH += "${LDFLAGS}"
