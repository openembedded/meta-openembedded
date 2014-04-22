DESCRIPTION = "The Apache HTTP Server is a powerful, efficient, and \
extensible web server."
SUMMARY = "Apache HTTP Server"
HOMEPAGE = "http://httpd.apache.org/"
DEPENDS = "expat-native pcre-native apr-native apr-util-native"
SECTION = "net"
LICENSE = "Apache-2.0"

inherit native

SRC_URI = "http://www.apache.org/dist/httpd/httpd-${PV}.tar.bz2"

S = "${WORKDIR}/httpd-${PV}"

LIC_FILES_CHKSUM = "file://LICENSE;md5=dbff5a2b542fa58854455bf1a0b94b83"
SRC_URI[md5sum] = "2ef4e65353497606b24fa9bb3e5a3c40"
SRC_URI[sha256sum] = "f78cc90dfa47caf3d83ad18fd6b4e85f237777c1733fc9088594b70ce2847603"

do_configure () {
    ./configure --with-apr=${STAGING_BINDIR_CROSS}/apr-1-config \
        --with-apr-util=${STAGING_BINDIR_CROSS}/apu-1-config \
        --prefix=${prefix} --datadir=${datadir}/apache2
}

do_install () {
    install -d ${D}${bindir} ${D}${libdir}
    cp server/gen_test_char ${D}${bindir}
    install -m 755 support/apxs ${D}${bindir}/
    install -m 755 httpd ${D}${bindir}/
    install -d ${D}${datadir}/apache2/build
    cp build/*.mk ${D}${datadir}/apache2/build
    cp build/instdso.sh ${D}${datadir}/apache2/build

    install -d ${D}${includedir}/apache2
    cp include/* ${D}${includedir}/apache2
    cp os/unix/os.h ${D}${includedir}/apache2
    cp os/unix/unixd.h ${D}${includedir}/apache2

    cp support/envvars-std ${D}${bindir}/envvars
    chmod 755 ${D}${bindir}/envvars
}

