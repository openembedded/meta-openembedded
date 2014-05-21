SUMMARY = "ExtUtils::InstallPaths - Build.PL install path logic made easy"
DESCRIPTION = "This module tries to make install path resolution as easy \
as possible."
SECTION = "libs"

HOMEPAGE = "http://search.cpan.org/~leont/ExtUtils-InstallPaths/"

LICENSE = "Artistic-1.0 | GPL-1.0+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=15bbcc2806d297df9e944b8955b38d82"

SRC_URI = "${CPAN_MIRROR}/authors/id/L/LE/LEONT/ExtUtils-InstallPaths-${PV}.tar.gz"
SRC_URI[md5sum] = "f0b00cc6c04653588a6298fa1f16c07f"
SRC_URI[sha256sum] = "d14cbff118e467900c2c488b55b83ef697d6eb1b8f592c0521f1d82848bf2156"

S = "${WORKDIR}/ExtUtils-InstallPaths-${PV}"

inherit cpan

RDEPENDS_${PN} = " perl-module-extutils-makemaker \
                   perl-module-data-dumper \
                   perl-module-test-more \
                   perl-module-file-temp \
"

BBCLASSEXTEND = "native"
