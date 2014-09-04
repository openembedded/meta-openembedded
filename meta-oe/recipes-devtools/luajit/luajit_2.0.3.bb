SUMMARY = "Just-In-Time Compiler for Lua"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=97b6446bbf9d55616838f821585c5978"
HOMEPAGE = "http://luajit.org"

SRC_URI = "http://luajit.org/download/LuaJIT-${PV}.tar.gz \
           file://0001-Do-not-strip-automatically-this-leaves-the-stripping.patch \
"
SRC_URI[md5sum] = "f14e9104be513913810cd59c8c658dc0"
SRC_URI[sha256sum] = "55be6cb2d101ed38acca32c5b1f99ae345904b365b642203194c585d27bebd79"

S = "${WORKDIR}/LuaJIT-${PV}"

inherit pkgconfig binconfig

BBCLASSEXTEND = "native"

do_configure_prepend() {
    sed -i -e s:/usr/local:${prefix}:g ${S}/Makefile
    sed -i -e s:/lib$:${base_libdir}:g ${S}/Makefile
}

EXTRA_OEMAKE = 'CROSS=${HOST_PREFIX} \
                TARGET_CFLAGS="${TOOLCHAIN_OPTIONS} ${HOST_CC_ARCH}" \
                TARGET_LDFLAGS="${TOOLCHAIN_OPTIONS}" \
                TARGET_SHLDFLAGS="${TOOLCHAIN_OPTIONS}"'
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
    rmdir ${D}${datadir}/lua/5.* \
          ${D}${datadir}/lua \
          ${D}${libdir}/lua/5.* \
          ${D}${libdir}/lua
}

PACKAGES += 'luajit-common'

FILES_${PN} += "${libdir}/libluajit-5.1.so.2 \
    ${libdir}/libluajit-5.1.so.${PV} \
"
FILES_${PN}-dev += "${libdir}/libluajit-5.1.a \
    ${libdir}/libluajit-5.1.so \
    ${libdir}/pkgconfig/luajit.pc \
"
FILES_luajit-common = "${datadir}/${BPN}-${PV}"

