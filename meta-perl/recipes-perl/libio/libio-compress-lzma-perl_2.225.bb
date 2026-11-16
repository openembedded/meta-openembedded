SUMMARY = "Perl interface to allow reading and writing of lzma files/buffers."
DESCRIPTION = "This module provides a Perl interface to allow reading and \
writing of lzma files/buffers."
HOMEPAGE = "https://metacpan.org/release/IO-Compress-Lzma"
SECTION = "libs"
LICENSE = "Artistic-1.0 OR GPL-1.0-or-later"

LIC_FILES_CHKSUM = "file://README;beginline=8;endline=10;md5=dcf5ec9168367166aaeabb92ee5754e1"

SRC_URI = "${CPAN_MIRROR}/authors/id/P/PM/PMQS/IO-Compress-Lzma-${PV}.tar.gz"

SRC_URI[sha256sum] = "bf3fb4b4b20a9cac8c1c85f3ae678a6117df16b59b62b8ffdd0ab2752ea3d4ba"

S = "${UNPACKDIR}/IO-Compress-Lzma-${PV}"

inherit cpan

RDEPENDS:${PN} += "\
    perl-module-autoloader \
    libcompress-raw-lzma-perl \
    libio-compress-perl \
"

BBCLASSEXTEND = "native"
