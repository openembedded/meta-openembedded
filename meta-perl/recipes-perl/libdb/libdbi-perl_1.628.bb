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

LIC_FILES_CHKSUM = "file://DBI.pm;beginline=8147;endline=8151;md5=7d9e154a9ca3c093d2422f7c692d5861"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/T/TI/TIMB/DBI-${PV}.tar.gz"
SRC_URI[md5sum] = "4273f8cc6ee3979ce448c7eb3f8a6a5a"
SRC_URI[sha256sum] = "46c834f4ba1b28c8d8a2db8095835a67fc69a9585761523aea3a74437a969b52"

S = "${WORKDIR}/DBI-${PV}"

inherit cpan

BBCLASSEXTEND = "native"
