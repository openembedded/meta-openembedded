DESCRIPTION = " a Just-In-Time Compiler for Lua"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=9511885992e4900014e3b13ca6372b7d"
HOMEPAGE = "http://luajit.org"

DEPENDS += "lua5.1"
PR = "r2"
SRC_URI = "http://luajit.org/download/LuaJIT-${PV}.tar.gz \
           file://disable_lj_vm_foldarith_for_spe.patch \
"
SRC_URI[md5sum] = "97a2b87cc0490784f54b64cfb3b8f5ad"
SRC_URI[sha256sum] = "deaed645c4a093c5fb250c30c9933c9131ee05c94b13262d58f6e0b60b338c15"

S = "${WORKDIR}/LuaJIT-${PV}"

inherit pkgconfig binconfig

do_configure_prepend() {
    sed -i -e s:/usr/local:${prefix}:g ${S}/Makefile
    sed -i -e s:/lib$:${base_libdir}:g ${S}/Makefile
}

EXTRA_OEMAKE = 'CROSS=${HOST_PREFIX} TARGET_CFLAGS="${TOOLCHAIN_OPTIONS}" TARGET_LDFLAGS="${TOOLCHAIN_OPTIONS}" TARGET_SHLDFLAGS="${TOOLCHAIN_OPTIONS}"'
EXTRA_OEMAKE_append_powerpc = ' HOST_CC="${BUILD_CC} -m32"'
EXTRA_OEMAKE_append_x86-64 = ' HOST_CC="${BUILD_CC}"'
EXTRA_OEMAKE_append_i586 = ' HOST_CC="${BUILD_CC} -m32"'
EXTRA_OEMAKE_append_powerpc64 = ' HOST_CC="${BUILD_CC}"'
EXTRA_OEMAKE_append_arm = ' HOST_CC="${BUILD_CC} -m32"'
EXTRA_OEMAKE_append_mips64 = ' HOST_CC="${BUILD_CC} -m32"'

do_compile () {
    oe_runmake
}

do_install () {
    oe_runmake 'DESTDIR=${D}' install
    rmdir ${D}${datadir}/lua/5.1 \
          ${D}${datadir}/lua \
          ${D}${libdir}/lua/5.1 \
          ${D}${libdir}/lua
}

PACKAGES += 'luajit-common'

FILES_${PN} += "${libdir}/libluajit-5.1.so.2 \
    ${libdir}/libluajit-5.1.so.2.0.0 \
"
FILES_${PN}-dev += "${libdir}/libluajit-5.1.a \
    ${libdir}/libluajit-5.1.so \
    ${libdir}/pkgconfig/luajit.pc \
"
FILES_luajit-common = "${datadir}/${BPN}-${PV}"

