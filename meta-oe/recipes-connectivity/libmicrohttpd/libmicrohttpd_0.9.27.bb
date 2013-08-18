DESCRIPTION = "GNU libmicrohttpd is a small C library that is supposed to make it easy to run an HTTP server as part of another application."
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=9331186f4f80db7da0e724bdd6554ee5"

DEPENDS = "libgcrypt curl"

SRC_URI = "ftp://ftp.nluug.nl/pub/gnu/libmicrohttpd//libmicrohttpd-${PV}.tar.gz"
SRC_URI[md5sum] = "a10496b7f1b495aaf6897584da52f51b"
SRC_URI[sha256sum] = "d532edf64c3a9da472114c2d10fc94606e3b31f21b883ec2e796ab8478f7e9cb"


inherit autotools lib_package

do_compile_append() {
	sed -i s:-L${STAGING_LIBDIR}::g libmicrohttpd.pc
}
