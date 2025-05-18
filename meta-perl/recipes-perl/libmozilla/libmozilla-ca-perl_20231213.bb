SUMMARY = "Mozilla's CA cert bundle in PEM format"
DESCRIPTION = "Mozilla::CA provides a copy of Mozilla's bundle of \
Certificate Authority certificates in a form that can be consumed by  \
modules and libraries based on OpenSSL."
HOMEPAGE = "https://metacpan.org/pod/Mozilla::CA"
BUGTRACKER = "https://github.com/libwww-perl/Mozilla-CA/issues"
SECTION = "libs"

LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://README;beginline=32;endline=39;md5=51e666dce556490a1132e937ad3f8729"

SRC_URI = "${CPAN_MIRROR}/authors/id/L/LW/LWP/Mozilla-CA-${PV}.tar.gz"
SRC_URI[sha256sum] = "421a12ba7cef443bd368671cee9c9b46a8b55c2ccf7ac76c42f99e23a06115df"

S = "${WORKDIR}/Mozilla-CA-${PV}"

inherit cpan ptest-perl

RDEPENDS:${PN}-ptest += "\
    perl-module-test-more \
"
BBCLASSEXTEND = "native"
