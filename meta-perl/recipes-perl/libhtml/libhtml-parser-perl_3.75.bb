DESCRIPTION = "This package contains the Parser.pm module with friends."
HOMEPAGE = "https://metacpan.org/release/CAPOEIRAB/HTML-Parser-3.75"
SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0-or-later"

LIC_FILES_CHKSUM = "file://README;beginline=992;endline=996;md5=cab05784294bb47dc319aeced156e96a"

DEPENDS += "perl"

SRC_URI = "${CPAN_MIRROR}/authors/id/C/CA/CAPOEIRAB/HTML-Parser-${PV}.tar.gz"

SRC_URI[sha256sum] = "ac6b5e25a8df7af54885201e91c45fb9ab6744c08cedc1a38fcc7d95d21193a9"

S = "${UNPACKDIR}/HTML-Parser-${PV}"

EXTRA_CPANFLAGS = "EXPATLIBPATH=${STAGING_LIBDIR} EXPATINCPATH=${STAGING_INCDIR}"

inherit cpan ptest-perl

do_compile() {
    export LIBC="$(find ${STAGING_DIR_TARGET}/${base_libdir}/ -name 'libc-*.so')"
    cpan_do_compile
}

RDEPENDS:${PN} += "\
    perl-module-exporter \
    perl-module-strict \
    perl-module-vars \
    perl-module-xsloader \
    libhtml-tagset-perl \
"

RDEPENDS:${PN}-ptest += "\
    liburi-perl \
    perl-module-config \
    perl-module-file-spec \
    perl-module-filehandle \
    perl-module-io-file \
    perl-module-selectsaver \
    perl-module-test \
    perl-module-test-more \
"

BBCLASSEXTEND = "native"
