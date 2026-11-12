DESCRIPTION = "Concurrency Kit provides a plethora of concurrency primitives, \
safe memory reclamation mechanisms and non-blocking data structures \
designed to aid in the design and implementation of high performance \
concurrent systems."

LICENSE = "Apache-2.0 AND BSD-2-Clause"
HOMEPAGE = "http://concurrencykit.org"
SECTION = "base"

PV = "0.7.2"
SRCREV = "981e14290df3d4339974eade5d8820dafd506b58"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a0b24c1a8f9ad516a297d055b0294231"
SRC_URI = "git://github.com/concurrencykit/ck.git;branch=master;protocol=https \
           file://0001-configure-Fix-compoiler-detection-logic-for-cross-co.patch"


COMPATIBLE_HOST = "(arm|aarch64|i.86|x86_64|powerpc|powerpc64|riscv32|riscv64).*-linux*"

inherit autotools-brokensep

PLAT:powerpc64 = "ppc64"
PLAT:powerpc64le = "ppc64"
PLAT:riscv32 = "riscv"
PLAT ?= "${HOST_ARCH}"

do_configure () {
    export PLATFORM=${PLAT}
    ${S}/configure \
    --prefix=${prefix} \
    --includedir=${includedir} \
    --libdir=${libdir}
}

do_compile () {
    oe_runmake
}

do_install () {
    oe_runmake 'DESTDIR=${D}' install
}

do_install:append () {
    # ck.pc is installed with cp -p, keeping the build user as owner
    chown root:root ${D}${libdir}/pkgconfig/ck.pc
}
