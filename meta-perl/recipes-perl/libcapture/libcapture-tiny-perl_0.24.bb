SUMMARY = "Capture::Tiny - Capture STDOUT and STDERR from Perl, XS or external programs."
DESCRIPTION = "Capture::Tiny provies a simple, portable way to capture \
almost anything sent to STDOUT or STDERR, regardless of whether it comes \
from Perl, from XS code or from an external program. Optionally, output can \
be teed so that it is captured while being passed through to the original \
filehandles. Yes, it even works on Windows (usually). Stop guessing which of \
a dozen capturing modules to use in any particular situation and just use \
this one."
SECTION = "libs"

HOMEPAGE = "http://search.cpan.org/~dagolden/Capture-Tiny/"

LICENSE = "Artistic-1.0 | GPL-1.0+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=37a4918a30ace24395020e5b8c03b83f"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/D/DA/DAGOLDEN/Capture-Tiny-${PV}.tar.gz"
SRC_URI[md5sum] = "718e9d62c4a27a3207cf8506cb99d846"
SRC_URI[sha256sum] = "9bcf6f8472f8ea50401536cb070ac0c7770837d155c4d6abe212759863aae065"

S = "${WORKDIR}/Capture-Tiny-${PV}"

inherit cpan

RDEPENDS_${PN} = " perl-module-scalar-util \
                   perl-module-io-file \
                   perl-module-extutils-makemaker \
                   perl-module-file-spec \
                   perl-module-exporter \
                   perl-module-carp \
                   perl-module-test-more \
                   perl-module-file-temp \
                   perl-module-lib \
                   perl-module-build \
"

BBCLASSEXTEND = "native"
