SUMMARY = "Low-Level Interface to lzma compresion library."
DESCRIPTION = "This module provides a Perl interface to allow reading and \
wrting of lzma, lzip and xz files/buffers."
HOMEPAGE = "https://metacpan.org/release/Compress-Raw-Lzma"
SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0-or-later"

LIC_FILES_CHKSUM = "file://README;beginline=8;endline=10;md5=db84d74e3df088dfbcfd6b30da2749c8"

SRC_URI = "${CPAN_MIRROR}/authors/id/P/PM/PMQS/Compress-Raw-Lzma-${PV}.tar.gz"

SRC_URI[sha256sum] = "1dcc4448969acad4550376476e3fcd2ed96bfaaae7d9622958fd8f9a1ab4c87d"

DEPENDS += "xz"

S = "${UNPACKDIR}/Compress-Raw-Lzma-${PV}"

inherit cpan

RDEPENDS:${PN} += "\
    perl-module-universal \
"

export LIBLZMA_INCLUDE = "-I${STAGING_DIR_HOST}${includedir}"
export LIBLZMA_LIB = "-I${STAGING_DIR_HOST}${libdir}"

do_compile() {
	export LIBC="$(find ${STAGING_DIR_TARGET}/${base_libdir}/ -name 'libc-*.so')"
	cpan_do_compile
}

BBCLASSEXTEND = "native"
