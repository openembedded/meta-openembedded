SUMMARY = "A Perl DBI driver for SQLite"
DESCRIPTION = "DBD::SQLite is a Perl DBI driver for SQLite, that includes the entire \
thing in the distribution. So in order to get a fast transaction capable \
RDBMS working for your perl project you simply have to install this \
module, and nothing else. \
"
HOMEPAGE = "http://search.cpan.org/~ishigaki/DBD-SQLite/"

SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0+"
DEPENDS += "libdbi-perl-native"
RDEPENDS_${PN} += "libdbi-perl \
                   sqlite3 \
                   perl-module-constant \
                   perl-module-locale \
                   perl-module-tie-hash \
"

LIC_FILES_CHKSUM = "file://LICENSE;md5=1726e2117494ba3e13e1c3d93f795360"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/I/IS/ISHIGAKI/DBD-SQLite-${PV}.tar.gz \
           file://sqlite-perl-test.pl \
"

SRC_URI[md5sum] = "b9876882186499583428b14cf5c0e29c"
SRC_URI[sha256sum] = "21fb65e740b6265512c82232b4ad8f75c19ac84c216830112656274eb8e375fb"

S = "${WORKDIR}/DBD-SQLite-${PV}"

inherit cpan

BBCLASSEXTEND = "native"

do_install_append() {
    if [ ${PERL_DBM_TEST} = "1" ]; then
        install -m 755 -D ${WORKDIR}/sqlite-perl-test.pl ${D}/${bindir}/sqlite-perl-test.pl
    fi
}

