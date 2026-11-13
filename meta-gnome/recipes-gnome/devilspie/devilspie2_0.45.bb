DESCRIPTION = "Devilspie2 is a window matching utility, allowing the user to perform scripted actions on windows as they are created"
HOMEPAGE = "http://www.gusnan.se/devilspie2"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=1ebbd3e34237af26da5dc08a4e440464"

DEPENDS = "gtk+3 glib-2.0 libwnck3 libxinerama lua virtual/libx11"

PV .= "+git"

SRCREV = "ab0a1a04315d683cbb086bd973e2bc78720832c2"

SRC_URI = "git://github.com/dsalt/devilspie2;branch=master;protocol=https \
    file://default.lua \
    file://devilspie2.desktop \
"


inherit features_check pkgconfig gettext

REQUIRED_DISTRO_FEATURES = "x11"

do_compile() {
    oe_runmake CC="${CC}" PREFIX="${prefix}" CPPFLAGS="${CPPFLAGS}" LDFLAGS=" -ldl -lm ${LDFLAGS}"
}

do_install() {
    oe_runmake DESTDIR="${D}" PREFIX="${prefix}" install
    install -d ${D}/${sysconfdir}/devilspie2
    install -m 644 ${UNPACKDIR}/default.lua ${D}/${sysconfdir}/devilspie2
    install -d ${D}/${sysconfdir}/xdg/autostart
    install -m 644 ${UNPACKDIR}/devilspie2.desktop ${D}/${sysconfdir}/xdg/autostart
}
