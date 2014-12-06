SUMMARY = "Debugging and profiling extension for PHP"
LICENSE = "Xdebug"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34df3a274aa12b795417c65634c07f16"

DEPENDS = "modphp"

PNBLACKLIST[xdebug] ?= "CONFLICT: depends on blocked modphp"

SRC_URI = "http://xdebug.org/files/xdebug-${PV}.tgz"

SRC_URI[md5sum] = "7e571ce8eb6fa969fd8263969019849d"
SRC_URI[sha256sum] = "adc6fe92dcff2368be94d20a5997aadb4d46d74551e2dd6602a704a35a195a6f"

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

FILES_${PN} += "${libdir}/php5/extensions/*/*.so"
FILES_${PN}-dbg += "${libdir}/php5/extensions/*/.debug"

