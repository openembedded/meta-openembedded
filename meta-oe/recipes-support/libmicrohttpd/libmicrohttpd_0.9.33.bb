DESCRIPTION = "A small C library that is supposed to make it easy to run an HTTP server as part of another application"
HOMEPAGE = "http://www.gnu.org/software/libmicrohttpd/"
LICENSE = "LGPL-2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=9331186f4f80db7da0e724bdd6554ee5"
SECTION = "net"
DEPENDS = "libgcrypt gnutls"

SRC_URI = "http://ftp.gnu.org/gnu/libmicrohttpd/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "013b10f9de1cda5448b29c81305354a3"
SRC_URI[sha256sum] = "d9587e8497548f820650cd607daf3615ea3272435779378983032c570f1cd559"

inherit autotools lib_package

EXTRA_OECONF += "-disable-static --enable-gnutls"

PACKAGECONFIG ?= "curl"
PACKAGECONFIG_append_class-target = "\
        ${@base_contains('DISTRO_FEATURES', 'largefile', 'largefile', '', d)} \
"
PACKAGECONFIG[largefile] = "--enable-largefile,--disable-largefile,,"
PACKAGECONFIG[curl] = "--enable-curl,--disable-curl,curl,"

do_compile_append() {
	sed -i s:-L${STAGING_LIBDIR}::g libmicrohttpd.pc
}

