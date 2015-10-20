SUMMARY = "Just-In-Time Compiler for Lua"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=3992f1fbae3b8b061f9056b7fcda8cc6"
HOMEPAGE = "http://luajit.org"

SRCREV = "776c6935ed807fc3b1ee6cd89a0cf682ffb7e9de"
SRC_URI = "git://luajit.org/git/luajit-2.0.git;protocol=http;branch=v${PV} \
"

SPIN = ".0-beta1"

S = "${WORKDIR}/git"

inherit pkgconfig binconfig

BBCLASSEXTEND = "native"

do_configure_prepend() {
    sed -i 's:PREFIX= /usr/local:PREFIX= ${prefix}:g' ${S}/Makefile
    sed -i 's:MULTILIB= lib:MULTILIB= ${baselib}:g' ${S}/Makefile
}

EXTRA_OEMAKE = 'CROSS=${HOST_PREFIX} \
                TARGET_CFLAGS="${TOOLCHAIN_OPTIONS} ${HOST_CC_ARCH}" \
                TARGET_LDFLAGS="${TOOLCHAIN_OPTIONS}" \
                TARGET_SHLDFLAGS="${TOOLCHAIN_OPTIONS}" TARGET_STRIP="/bin/echo"'
EXTRA_OEMAKE_append_powerpc = ' HOST_CC="${BUILD_CC} -m32"'
EXTRA_OEMAKE_append_x86 = ' HOST_CC="${BUILD_CC} -m32"'
EXTRA_OEMAKE_append_x86-64 = ' HOST_CC="${BUILD_CC}"'
EXTRA_OEMAKE_append_powerpc64 = ' HOST_CC="${BUILD_CC}"'
EXTRA_OEMAKE_append_arm = ' HOST_CC="${BUILD_CC} -m32"'
EXTRA_OEMAKE_append_mips64 = ' HOST_CC="${BUILD_CC} -m32"'

do_compile () {
    oe_runmake
}

do_install () {
    oe_runmake 'DESTDIR=${D}' 'TARGET_STRIP=/bin/echo' install
    ln -s ${bindir}/${BPN}-${PV}${SPIN} ${D}${bindir}/luajit
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
FILES_luajit-common = "${datadir}/${BPN}-${PV}${SPIN}"

