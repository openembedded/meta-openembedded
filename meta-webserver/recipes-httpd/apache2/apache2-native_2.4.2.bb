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
SRC_URI[md5sum] = "6bb12f726e22656f0ad2baf91f1f8329"
SRC_URI[sha256sum] = "5382f9c507d3d02706e33d6308ea041f39e8511b5948aef0ca188df8f90159b8"

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

