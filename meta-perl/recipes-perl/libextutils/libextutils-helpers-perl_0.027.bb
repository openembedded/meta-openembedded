SUMMARY = "ExtUtils::Helpers - Various portability utilities for module builders"
DESCRIPTION = "This module provides various portable helper function for module building modules."
SECTION = "libs"

HOMEPAGE = "http://search.cpan.org/~leont/ExtUtils-Helpers/"

LICENSE = "Artistic-1.0 | GPL-1.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c0280160e4f26faafef507664884bf63"

SRC_URI = "${CPAN_MIRROR}/authors/id/L/LE/LEONT/ExtUtils-Helpers-${PV}.tar.gz"
SRC_URI[sha256sum] = "9d592131dc5845a86dc28be9143f764e73cb62db06fedf50a895be1324b6cec5"

S = "${WORKDIR}/ExtUtils-Helpers-${PV}"

inherit cpan

RDEPENDS:${PN} = " perl-module-file-copy \
                   perl-module-extutils-makemaker \
                   perl-module-exporter \
                   perl-module-carp \
                   perl-module-test-more \
                   perl-module-text-parsewords \
                   perl-module-load \
                   perl-module-file-temp \
                   perl-module-file-spec-functions \
"

BBCLASSEXTEND = "native"
