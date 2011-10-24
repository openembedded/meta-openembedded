DESCRIPTION = "GNU libmicrohttpd is a small C library that is supposed to make it easy to run an HTTP server as part of another application."
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=9331186f4f80db7da0e724bdd6554ee5"

DEPENDS = "libgcrypt curl"

SRC_URI = "ftp://ftp.nluug.nl/pub/gnu/libmicrohttpd//libmicrohttpd-${PV}.tar.gz"
SRC_URI[md5sum] = "61698da6aa04744ea076c327f66fc05a"
SRC_URI[sha256sum] = "0cb09e26678d1531a4fd40a748abff5142bfdad3ae11cfe3bb7ad7317a99e371"

inherit autotools lib_package

do_compile_append() {
	sed -i s:-L${STAGING_LIBDIR}::g libmicrohttpd.pc
}
