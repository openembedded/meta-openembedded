SUMMARY = "Perl interface to allow reading and writing of lzma files/buffers."
DESCRIPTION = "This module provides a Perl interface to allow reading and \
writing of lzma files/buffers."
HOMEPAGE = "https://metacpan.org/release/IO-Compress-Lzma"
SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0-or-later"

LIC_FILES_CHKSUM = "file://README;beginline=8;endline=10;md5=db84d74e3df088dfbcfd6b30da2749c8"

SRC_URI = "${CPAN_MIRROR}/authors/id/P/PM/PMQS/IO-Compress-Lzma-${PV}.tar.gz"

SRC_URI[sha256sum] = "f4ee89f3af99e59ad0742fd7b6358f9159ef513f1f6943d7140fb1bfa550dd38"

S = "${UNPACKDIR}/IO-Compress-Lzma-${PV}"

inherit cpan

RDEPENDS:${PN} += "\
    perl-module-autoloader \
    libcompress-raw-lzma-perl \
    libio-compress-perl \
"

BBCLASSEXTEND = "native"
