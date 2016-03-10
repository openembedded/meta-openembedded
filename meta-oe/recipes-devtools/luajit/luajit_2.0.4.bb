SUMMARY = "Just-In-Time Compiler for Lua"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=3992f1fbae3b8b061f9056b7fcda8cc6"
HOMEPAGE = "http://luajit.org"

SRC_URI = "http://luajit.org/download/LuaJIT-${PV}.tar.gz \
           file://0001-Do-not-strip-automatically-this-leaves-the-stripping.patch \
"
SRC_URI[md5sum] = "dd9c38307f2223a504cbfb96e477eca0"
SRC_URI[sha256sum] = "620fa4eb12375021bef6e4f237cbd2dd5d49e56beb414bee052c746beef1807d"

S = "${WORKDIR}/LuaJIT-${PV}"

inherit pkgconfig binconfig

BBCLASSEXTEND = "native"

do_configure_prepend() {
    sed -i 's:PREFIX= /usr/local:PREFIX= ${prefix}:g' ${S}/Makefile
    sed -i 's:MULTILIB= lib:MULTILIB= ${baselib}:g' ${S}/Makefile
}

EXTRA_OEMAKE = 'CROSS=${HOST_PREFIX} \
                TARGET_CFLAGS="${TOOLCHAIN_OPTIONS} ${HOST_CC_ARCH}" \
                TARGET_LDFLAGS="${TOOLCHAIN_OPTIONS}" \
                TARGET_SHLDFLAGS="${TOOLCHAIN_OPTIONS}"'
EXTRA_OEMAKE_append_powerpc = ' HOST_CC="${BUILD_CC} -m32"'
EXTRA_OEMAKE_append_x86 = ' HOST_CC="${BUILD_CC} -m32"'
EXTRA_OEMAKE_append_x86-64 = ' HOST_CC="${BUILD_CC}"'
EXTRA_OEMAKE_append_powerpc64 = ' HOST_CC="${BUILD_CC}"'
EXTRA_OEMAKE_append_arm = ' HOST_CC="${BUILD_CC} -m32"'
EXTRA_OEMAKE_append_mips64 = ' HOST_CC="${BUILD_CC} -m32"'

DEPENDS_append_class_target = " luajit-native"
EXTRA_OEMAKE_append_class_target = " HOST_LUA=luajit"

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

