DESCRIPTION = "Concurrency Kit provides a plethora of concurrency primitives, \
safe memory reclamation mechanisms and non-blocking data structures \
designed to aid in the design and implementation of high performance \
concurrent systems."

LICENSE = "BSD & Apache-2.0"
HOMEPAGE = "http://concurrencykit.org"
SECTION = "base"

PV = "0.4.3+git${SRCPV}"
SRCREV = "900d203aa9e41288545368ea40da0bee89f7907f"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f12611e65e064515ce264249b2bdea98"
SRC_URI = "git://github.com/concurrencykit/ck.git;protocol=https \
           file://cross.patch \
"

S = "${WORKDIR}/git"

COMPATIBLE_HOST = "(i.86|x86_64|powerpc|powerpc64).*-linux*"

inherit autotools-brokensep

PLAT_powerpc64 = "ppc64"
PLAT ?= "${HOST_ARCH}"

do_configure () {
    export PLATFORM=${PLAT}
    export COMPILER='gcc'
    ${S}/configure \
    --prefix=${prefix} \
    --includedir=${includedir} \
    --libdir=${libdir} \
}

do_compile () {
    oe_runmake
}

do_install () {
    oe_runmake 'DESTDIR=${D}' install
}
