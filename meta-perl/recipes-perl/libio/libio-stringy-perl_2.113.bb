SUMMARY = "IO-stringy - I/O on in-core objects like strings and arrays"
DESCRIPTION = "This toolkit primarily provides modules for performing both \
traditional and object-oriented i/o on things *other* than normal \
filehandles; in particular, IO::Scalar, IO::ScalarArray, and IO::Lines."

HOMEPAGE = "http://www.zeegee.com/products/IO-stringy/"
SECTION = "devel"
LICENSE = "Artistic-1.0 OR GPL-1.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=01406e4ff2e60d88d42ef1caebdd0011"


SRC_URI = "${CPAN_MIRROR}/authors/id/C/CA/CAPOEIRAB/IO-Stringy-${PV}.tar.gz"
SRC_URI[sha256sum] = "51220fcaf9f66a639b69d251d7b0757bf4202f4f9debd45bdd341a6aca62fe4e"

S = "${UNPACKDIR}/IO-Stringy-${PV}"

inherit cpan

RPROVIDES:${PN} += " libio-atomicfile-perl \
    libio-innerfile-perl \
    libio-lines-perl \
    libio-scalar-perl \
    libio-scalararray-perl \
    libio-wrap-perl \
    libio-wraptie-perl \
    libio-wraptie-master-perl \
    libio-wraptie-slave-perl \
"

BBCLASSEXTEND = "native"
