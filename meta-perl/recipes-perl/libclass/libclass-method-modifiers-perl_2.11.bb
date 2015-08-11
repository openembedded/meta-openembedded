SUMMARY = "Class::Method::Modifiers - provides Moose-like method modifiers"
DESCRIPTION = "Method modifiers are a convenient feature from the CLOS \
(Common Lisp Object System) world."

SECTION = "libs"

HOMEPAGE = "https://github.com/moose/Class-Method-Modifiers/"

LICENSE = "Artistic-1.0 | GPL-1.0+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=16fd0ec7b73c0e158426f753943f1058"

SRC_URI = "${CPAN_MIRROR}/authors/id/E/ET/ETHER/Class-Method-Modifiers-${PV}.tar.gz"
SRC_URI[md5sum] = "c6982e9e89325713d5afaff82cfe1744"
SRC_URI[sha256sum] = "debf979fbd9ac25cc6fe7d1cab81103c60a21f7027d2a9b4bf5cb264e313d392"

S = "${WORKDIR}/Class-Method-Modifiers-${PV}"

inherit cpan

RDEPENDS_${PN} = " perl-module-b \
                   perl-module-base \
                   perl-module-carp \
                   perl-module-exporter \
                   perl-module-strict \
                   perl-module-warnings \
"

BBCLASSEXTEND = "native"
