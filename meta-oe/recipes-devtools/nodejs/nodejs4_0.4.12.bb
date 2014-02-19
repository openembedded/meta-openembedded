SUMMARY = "nodeJS Evented I/O for V8 JavaScript"
HOMEPAGE = "http://nodejs.org"
LICENSE = "MIT & zlib"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d6237f3a840aef5b7880fb4e49eecfe5"

DEPENDS = "openssl"

SRC_URI = " \
    http://nodejs.org/dist/node-v${PV}.tar.gz \
    file://libev-cross-cc_${PV}.patch \
"
SRC_URI[md5sum] = "a6375eaa43db5356bf443e25b828ae16"
SRC_URI[sha256sum] = "c01af05b933ad4d2ca39f63cac057f54f032a4d83cff8711e42650ccee24fce4"

S = "${WORKDIR}/node-v${PV}"

# v8 errors out if you have set CCACHE
CCACHE = ""

do_configure () {
    sed -i -e 's:/usr/lib:${STAGING_LIBDIR}:g' wscript
    sed -i -e 's:/usr/local/lib:${STAGING_LIBDIR}:g' wscript
    ./configure --prefix=${prefix} --without-snapshot
}

do_compile () {
    make
}

do_install () {
    oe_runmake install DESTDIR=${D}

    # fix namespace conflicts with other nodejs recipes
    mv ${D}${bindir}/node ${D}${bindir}/node4
    mv ${D}${bindir}/node-waf ${D}${bindir}/node4-waf

    mv ${D}${includedir}/node ${D}${includedir}/node4

    mv ${D}${libdir}/node ${D}${libdir}/node4
    mv ${D}${libdir}/pkgconfig/nodejs.pc ${D}${libdir}/pkgconfig/nodejs4.pc
    sed -i -e s:include/node:include/node4: ${D}${libdir}/pkgconfig/nodejs4.pc

    mv ${D}${datadir}/man/man1/node.1 ${D}${datadir}/man/man1/node4.1
}

FILES_${PN} += "${libdir}/node4/wafadmin"
BBCLASSEXTEND = "native"
