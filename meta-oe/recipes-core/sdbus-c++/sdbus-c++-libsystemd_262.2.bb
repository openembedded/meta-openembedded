require recipes-core/systemd/systemd.inc

SUMMARY = "libsystemd static library"
DESCRIPTION = "libsystemd static library built specifically as an integral component of sdbus-c++"

SECTION = "libs"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL2.1;md5=be0aaf4a380f73f7e00b420a007368f2"

inherit meson pkgconfig

DEPENDS += "gperf-native gettext-native util-linux libcap util-linux python3-jinja2-native"

SRC_URI += "file://static-libsystemd-pkgconfig.patch"

PACKAGECONFIG ??= "gshadow idn"
PACKAGECONFIG:remove:libc-musl = " gshadow idn"
PACKAGECONFIG[gshadow] = "-Dgshadow=true,-Dgshadow=false"
PACKAGECONFIG[idn] = "-Didn=true,-Didn=false"

DEPENDS:append:libc-musl = " libucontext"
CFLAGS:append:libc-musl = " -D__UAPI_DEF_ETHHDR=0 "

# Enable systemd's in-tree musl support (mirrors oe-core's systemd recipe).
EXTRA_OEMESON += "-Dlibc=${TCLIBC}"

EXTRA_OEMESON += "-Dstatic-libsystemd=pic"


RDEPENDS:${PN}-dev = ""

do_compile() {
    ninja -v ${PARALLEL_MAKE} libsystemd.a
    ninja -v ${PARALLEL_MAKE} src/libsystemd/libsystemd.pc
}

do_install () {
    install -d ${D}${libdir}
    install ${B}/libsystemd.a ${D}${libdir}

    install -d ${D}${includedir}/systemd
    install ${S}/src/systemd/*.h ${D}${includedir}/systemd

    install -d ${D}${libdir}/pkgconfig
    install ${B}/src/libsystemd/libsystemd.pc ${D}${libdir}/pkgconfig
}

# The static libsystemd uses getcontext/makecontext/swapcontext from libucontext on musl
do_install:append:libc-musl () {
    sed -i -e '/^Libs:/s/$/ -lucontext/' ${D}${libdir}/pkgconfig/libsystemd.pc
}
