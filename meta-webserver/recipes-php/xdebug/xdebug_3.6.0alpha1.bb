SUMMARY = "Debugging and profiling extension for PHP"
HOMEPAGE = "https://xdebug.org/"
LICENSE = "Xdebug-1.03"
LIC_FILES_CHKSUM = "file://LICENSE;md5=afd6ce4aa04fdc346e5b3c6e634bd75c"

DEPENDS = "php re2c-native"

# 3.6.0alpha1 is the first release that supports PHP 8.6
PV = "3.6.0~alpha1"
XDEBUG_VERSION = "3.6.0alpha1"

SRC_URI = "https://xdebug.org/files/xdebug-${XDEBUG_VERSION}.tgz"

SRC_URI[sha256sum] = "5f464dfbba2f2aec384f9a10cb42cbfb2b3c07274b70491c46080b707271fcde"

S = "${UNPACKDIR}/xdebug-${XDEBUG_VERSION}"

UPSTREAM_CHECK_URI = "https://github.com/xdebug/xdebug/releases"
UPSTREAM_CHECK_REGEX = "releases/tag/(?P<pver>\d+(\.\d+)+)$"

inherit autotools

EXTRA_OECONF += "--enable-xdebug -with-php-config=${STAGING_BINDIR_CROSS}/php-config"

do_configure() {
    cd ${S}
    ${STAGING_BINDIR_CROSS}/phpize
    cd ${B}

    # Running autoreconf as autotools_do_configure would do here
    # breaks the libtool configuration resulting in a failure later
    # in do_compile. It's possible this may be fixable, however the
    # easiest course of action for the moment is to avoid doing that.
    oe_runconf
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}
}

FILES:${PN} += "${libdir}/php*/extensions/*/*.so"
FILES:${PN}-dbg += "${libdir}/php*/extensions/*/.debug"
