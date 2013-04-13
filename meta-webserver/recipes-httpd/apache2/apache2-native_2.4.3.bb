DESCRIPTION = "The Apache HTTP Server is a powerful, efficient, and \
extensible web server."
SUMMARY = "Apache HTTP Server"
HOMEPAGE = "http://httpd.apache.org/"
DEPENDS = "expat-native pcre-native apr-native apr-util-native"
SECTION = "net"
LICENSE = "Apache-2.0"
PR = "r0"

inherit native

SRC_URI = "http://www.apache.org/dist/httpd/httpd-${PV}.tar.bz2"

S = "${WORKDIR}/httpd-${PV}"

LIC_FILES_CHKSUM = "file://LICENSE;md5=eff226ae95d0516d6210ed77dfdf2dcc"
SRC_URI[md5sum] = "87aaf7bc7e8715f0455997bb8c6791aa"
SRC_URI[sha256sum] = "d82102b9c111f1892fb20a2bccf4370de579c6521b2f172ed0b36f2759fb249e"

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

