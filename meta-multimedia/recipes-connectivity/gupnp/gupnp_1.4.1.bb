SUMMARY = "UPnP framework"
DESCRIPTION = "GUPnP is an elegant, object-oriented open source framework for creating UPnP  devices and control points, written in C using GObject and libsoup. The GUPnP API is intended to be easy to use, efficient and flexible. It provides the same set of features as libupnp, but shields the developer from most of UPnP's internals."
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "e2fsprogs gssdp libsoup-2.4 libxml2"

inherit meson pkgconfig vala gobject-introspection ptest

SRC_URI = "${GNOME_MIRROR}/${BPN}/1.4/${BPN}-${PV}.tar.xz"
SRC_URI[sha256sum] = "899196b5e66f03b8e25f046a7a658cd2a6851becb83f2d55345ab3281655dc0c"
SRC_URI += "file://run-ptest"

SYSROOT_PREPROCESS_FUNCS += "gupnp_sysroot_preprocess"

gupnp_sysroot_preprocess () {
    install -d ${SYSROOT_DESTDIR}${bindir_crossscripts}/
    install -m 755 ${D}${bindir}/gupnp-binding-tool* ${SYSROOT_DESTDIR}${bindir_crossscripts}/
}

FILES:${PN}-dev += "${bindir}/gupnp-binding-tool*"

RDEPENDS:${PN}-dev = "python3 python3-xml"

do_configure:prepend(){
    # change the test-datadir from source-folder to ptest-folder
    sed -i "s!\(-DDATA_PATH=\"\).*!\1${PTEST_PATH}/tests/data\"',!" ${S}/tests/meson.build
}

do_install_ptest(){
    install -d ${D}${PTEST_PATH}/tests
    find ${B}/tests -type f -executable -exec install {} ${D}${PTEST_PATH}/tests/ \;
    cp -r ${S}/tests/data ${D}${PTEST_PATH}/tests/
}
