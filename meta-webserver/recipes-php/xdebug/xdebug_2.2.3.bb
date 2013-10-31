SUMMARY = "Debugging and profiling extension for PHP"
LICENSE = "Xdebug"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34df3a274aa12b795417c65634c07f16"

DEPENDS = "modphp"


SRC_URI = "http://xdebug.org/files/xdebug-${PV}.tgz"

SRC_URI[md5sum] = "e49cec9861b45dc0b36eae33bf8a14fa"
SRC_URI[sha256sum] = "b351872da46ed8378dff90a87673f5ec1e0bdd94324558ebc898e1d115e9d71c"

inherit autotools

EXTRA_OECONF += "--enable-xdebug -with-php-config=${STAGING_BINDIR_CROSS}/php-config"

do_configure() {
    ${STAGING_BINDIR_CROSS}/phpize

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

