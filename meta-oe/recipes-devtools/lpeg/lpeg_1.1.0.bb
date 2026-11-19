SUMMARY = "Parsing Expression Grammars for Lua"
DESCRIPTION = "LPeg is a pattern-matching library for Lua based on Parsing \
Expression Grammars. It is built as a shared library with a soname, so that \
programs embedding LuaJIT such as neovim can link against it; it is \
also installed as a Lua module for LuaJIT."
HOMEPAGE = "https://www.inf.puc-rio.br/~roberto/lpeg/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://lpeg.html;beginline=1388;endline=1421;md5=c6b4557382727a792e7134685abbf862"

SRC_URI = "https://www.inf.puc-rio.br/~roberto/lpeg/lpeg-${PV}.tar.gz"
SRC_URI[sha256sum] = "4b155d67d2246c1ffa7ad7bc466c1ea899bbc40fef0257cc9c03cecbaed4352a"

DEPENDS = "luajit"

inherit pkgconfig

do_compile() {
    oe_runmake lpeg.so \
        CC="${CC}" \
        CFLAGS="${CFLAGS} -std=c99 -fPIC $(pkg-config --cflags luajit)" \
        DLLFLAGS="${LDFLAGS} -shared -Wl,-soname,liblpeg.so.1"
}

do_install() {
    install -d ${D}${libdir}/lua/5.1
    install -m 0755 ${S}/lpeg.so ${D}${libdir}/liblpeg.so.${PV}
    ln -sf liblpeg.so.${PV} ${D}${libdir}/liblpeg.so.1
    ln -sf liblpeg.so.${PV} ${D}${libdir}/liblpeg.so
    ln -sf ../../liblpeg.so.1 ${D}${libdir}/lua/5.1/lpeg.so
}

FILES:${PN} += "${libdir}/lua"
INSANE_SKIP:${PN} = "dev-so"

BBCLASSEXTEND = "native"
