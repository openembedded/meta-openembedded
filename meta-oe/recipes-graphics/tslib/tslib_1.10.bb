SUMMARY = "An abstraction layer for touchscreen panel events"
DESCRIPTION = "Tslib is an abstraction layer for touchscreen panel \
events, as well as a filter stack for the manipulation of those events. \
Tslib is generally used on embedded devices to provide a common user \
space interface to touchscreen functionality."
HOMEPAGE = "http://tslib.berlios.de/"

AUTHOR = "Russell King w/ plugins by Chris Larson et. al."
SECTION = "base"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=43d8c912b273b3dc220596d5a5e79ea2"

SRC_URI = "https://github.com/kergoth/tslib/releases/download/${PV}/tslib-${PV}.tar.xz;downloadfilename=tslib-${PV}.tar.xz \
           file://ts.conf \
           file://tslib.sh \
"

SRC_URI[md5sum] = "7ec66da49679ce3eb9bf16f346eaa63d"
SRC_URI[sha256sum] = "26ce1df4647dc8d16f6247062f30a8a03c34165dd19e042d0b2df8860da265b2"

UPSTREAM_CHECK_URI = "https://github.com/kergoth/tslib/releases"

inherit autotools pkgconfig

EXTRA_OECONF = "--enable-shared --disable-h3600 --enable-input --disable-corgi --disable-collie --disable-mk712 --disable-arctic2 --disable-ucb1x00"

do_install_prepend() {
    install -m 0644 ${WORKDIR}/ts.conf ${S}/etc/ts.conf
}

do_install_append() {
    install -d ${D}${sysconfdir}/profile.d/
    install -m 0755 ${WORKDIR}/tslib.sh ${D}${sysconfdir}/profile.d/
}

# People should consider using udev's /dev/input/touchscreen0 symlink
# instead of detect-stylus
#RDEPENDS_tslib-conf_weird-machine = "detect-stylus"
RPROVIDES_tslib-conf = "libts-0.0-conf"

PACKAGES =+ "tslib-conf tslib-tests tslib-calibrate"
DEBIAN_NOAUTONAME_tslib-conf = "1"
DEBIAN_NOAUTONAME_tslib-tests = "1"
DEBIAN_NOAUTONAME_tslib-calibrate = "1"

RDEPENDS_${PN} = "tslib-conf"
RRECOMMENDS_${PN} = "pointercal"

FILES_${PN}-dev += "${libdir}/ts/*.la"
FILES_tslib-conf = "${sysconfdir}/ts.conf ${sysconfdir}/profile.d/tslib.sh ${datadir}/tslib"
FILES_${PN} = "${libdir}/*.so.* ${libdir}/ts/*.so*"
FILES_tslib-calibrate += "${bindir}/ts_calibrate"
FILES_tslib-tests = "${bindir}/ts_harvest ${bindir}/ts_print ${bindir}/ts_print_raw ${bindir}/ts_test"
