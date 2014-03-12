SUMMARY = "Just-In-Time Compiler for Lua"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=1793ef52a5d577794cd886a501a7f861"
HOMEPAGE = "http://luajit.org"

DEPENDS += "lua5.1"
SRC_URI = "http://luajit.org/download/LuaJIT-${PV}.tar.gz;name=tarball \
           file://0001-Do-not-strip-automatically-this-leaves-the-stripping.patch \
          "
SRC_URI[tarball.md5sum] = "112dfb82548b03377fbefbba2e0e3a5b"
SRC_URI[tarball.sha256sum] = "c05202974a5890e777b181908ac237625b499aece026654d7cc33607e3f46c38"

SRC_URI_append_fslmachine = " file://ppc-fixplt.patch "

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
    ${libdir}/libluajit-5.1.so.${PV} \
"
FILES_${PN}-dev += "${libdir}/libluajit-5.1.a \
    ${libdir}/libluajit-5.1.so \
    ${libdir}/pkgconfig/luajit.pc \
"
FILES_luajit-common = "${datadir}/${BPN}-${PV}"

