SUMMARY = "ExtUtils::Config - A wrapper for perl's configuration"
DESCRIPTION = "ExtUtils::Config is an abstraction around the %Config hash."
SECTION = "libs"

HOMEPAGE = "http://search.cpan.org/~leont/ExtUtils-Config/"

LICENSE = "Artistic-1.0 | GPL-1.0+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b1b813683bd988732e7fd6a796bf7f47"

SRC_URI = "${CPAN_MIRROR}/authors/id/L/LE/LEONT/ExtUtils-Config-${PV}.tar.gz"
SRC_URI[md5sum] = "2829c0dfa8a7e51b3f582efbee4bb128"
SRC_URI[sha256sum] = "2c1465078b876fd16a90507092805265528c2532d4937b03547a6dbdb8ac0eef"

S = "${WORKDIR}/ExtUtils-Config-${PV}"

inherit cpan

RDEPENDS_${PN} = " perl-module-extutils-makemaker \
                   perl-module-data-dumper \
                   perl-module-test-more \
                   perl-module-file-temp \
"

BBCLASSEXTEND = "native"
