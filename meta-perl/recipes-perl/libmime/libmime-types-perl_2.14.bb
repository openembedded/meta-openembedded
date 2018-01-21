SUMMARY = "MIME::Types - Definition of MIME types"
DESCRIPTION = "MIME types are used in MIME compliant lines, for instance \
as part of e-mail and HTTP traffic, to indicate the type of content which \
is transmitted. Sometimes real knowledge about a mime-type is need.\
\n\
This module maintains a set of MIME::Type objects, which each describe \
one known mime type."
HOMEPAGE = "http://search.cpan.org/~markov/MIME-Types-${PV}"
SECTION = "libraries"

LICENSE = "Artistic-1.0|GPLv1+"
LIC_FILES_CHKSUM = "file://META.yml;beginline=11;endline=11;md5=963ce28228347875ace682de56eef8e8"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/M/MA/MARKOV/MIME-Types-${PV}.tar.gz \
           file://run-ptest \
          "
SRC_URI[md5sum] = "c1b9b7dea40fbb96c0e0048d10d66268"
SRC_URI[sha256sum] = "4049cf0fc05205893f25fdbe07d1ab12bfc72259517db2c3348c1d1059730070"

S = "${WORKDIR}/MIME-Types-${PV}"

inherit cpan ptest

do_install_ptest () {
    cp -r ${B}/t ${D}${PTEST_PATH}
}
