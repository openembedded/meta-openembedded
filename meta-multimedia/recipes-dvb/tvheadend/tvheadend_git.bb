SUMMARY = "Tvheadend: TV streaming server and recorder"
HOMEPAGE = "https://tvheadend.org/"

DEPENDS = "avahi cmake-native gettext-native libpcre2 libdvbcsa openssl zlib python-native"

LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=9cae5acac2e9ee2fc3aec01ac88ce5db"

SRC_URI = "git://github.com/tvheadend/tvheadend.git"

inherit gitpkgv
SRCREV = "${AUTOREV}"
PV = "4.3+git${SRCPV}"
PKGV = "4.3+git${GITPKGV}"

S = "${WORKDIR}/git"

PACKAGECONFIG ?= ""
PACKAGECONFIG[uriparser] = "--enable-uriparser,--disable-uriparser,uriparser"

do_configure() {
    ./configure ${PACKAGECONFIG_CONFARGS} \
                --prefix=${prefix} \
                --libdir=${libdir} \
                --bindir=${bindir} \
                --datadir=${datadir} \
                --arch=${TARGET_ARCH} \
                --disable-libav \
                --disable-ffmpeg_static \
                --disable-hdhomerun_static
}

do_install() {
    oe_runmake install DESTDIR=${D}
}

FILES_${PN} += "${datadir}/${BPN}"
