DESCRIPTION = "Lzip is a lossless data compressor based on the LZMA algorithm"
HOMEPAGE = "http://lzip.nongnu.org/lzip.html"
SECTION = "console/utils"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949"

SRC_URI = "http://download.savannah.gnu.org/releases/lzip/lzip-${PV}.tar.gz"

# Only the latest version is available in .tar.gz format from savannah.gnu.org,
# FreeBSD distfiles is known to have version 1.13.
MIRRORS += "http://download.savannah.gnu.org/releases/lzip/ ftp://ftp.freebsd.org/pub/FreeBSD/ports/distfiles/"

SRC_URI[md5sum] = "39a4b06952ee45274525dc0582cdd651"
SRC_URI[sha256sum] = "7ff5cc521560edb2a0a6cdf258cf3afdaeb1dbcc354d96d011d0dd7ec584cbe7"

CONFIGUREOPTS = "\
    '--srcdir=${S}' \
    '--prefix=${prefix}' \
    '--exec-prefix=${exec_prefix}' \
    '--bindir=${bindir}' \
    '--datadir=${datadir}' \
    '--infodir=${infodir}' \
    '--sysconfdir=${sysconfdir}' \
    'CXX=${CXX}' \
    'CPPFLAGS=${CPPFLAGS}' \
    'CXXFLAGS=${CXXFLAGS}' \
    'LDFLAGS=${LDFLAGS}' \
"
EXTRA_OEMAKE = ""

B = "${S}/obj"
do_configure () {
    ${S}/configure ${CONFIGUREOPTS}
}

do_install () {
    oe_runmake 'DESTDIR=${D}' install
}

BBCLASSEXTEND += "native nativesdk"
