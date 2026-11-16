SUMMARY = "Perl interface to the zlib compression library."
DESCRIPTION = "The Compress::Raw::Zlib module provides a Perl interface \
to the zlib compression library (see 'AUTHOR' for details about where to \
get zlib)."
HOMEPAGE = "https://metacpan.org/release/Compress-Raw-Zlib"
SECTION = "libs"
LICENSE = "Artistic-1.0 OR GPL-1.0-or-later"

LIC_FILES_CHKSUM = "file://README;beginline=8;endline=17;md5=9de8ad2929a6aa38a8c1f3950764d522"

SRC_URI = "${CPAN_MIRROR}/authors/id/P/PM/PMQS/Compress-Raw-Zlib-${PV}.tar.gz"

SRC_URI[sha256sum] = "80dc104a733976eba40dbe9272cd32ebf183195204e0bdd30e214a37d2ac42cb"

DEPENDS += "zlib"

S = "${UNPACKDIR}/Compress-Raw-Zlib-${PV}"

inherit cpan

export BUILD_ZLIB = "0"

do_compile() {
	export LIBC="$(find ${STAGING_DIR_TARGET}/${base_libdir}/ -name 'libc-*.so')"
	cpan_do_compile
}

BBCLASSEXTEND = "native"
