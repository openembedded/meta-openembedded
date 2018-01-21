SUMMARY = "The Perl Database Interface"
DESCRIPTION = "DBI is a database access Application Programming Interface \
(API) for the Perl Language. The DBI API Specification defines a set \
of functions, variables and conventions that provide a consistent \
database interface independent of the actual database being used. \
"
HOMEPAGE = "http://search.cpan.org/dist/DBI/"
SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0+"
RDEPENDS_${PN} = " perl-module-carp \
                   perl-module-exporter \
                   perl-module-exporter-heavy \
                   perl-module-dynaloader \
"

LIC_FILES_CHKSUM = "file://DBI.pm;md5=62a0479400fd3c7f793568af2e1f08ee;beginline=8147;endline=8151"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/T/TI/TIMB/DBI-${PV}.tar.gz"
SRC_URI[md5sum] = "f9bf9775b3dbaabc4630b2b29941aa89"
SRC_URI[sha256sum] = "8e2cb3d6a8425bd68702aebbeee01e754ee11ad00e7f4f9a61b75483de104e8c"

S = "${WORKDIR}/DBI-${PV}"

inherit cpan

BBCLASSEXTEND = "native"
