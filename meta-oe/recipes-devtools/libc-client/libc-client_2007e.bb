SUMMARY = "UW c-client library for mail protocols"
SECTION = "devel"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a6a4ddbb7cd2999f6827ee143f6fcd97"

DEPENDS = "openssl libpam"

PR = "r1"

SRC_URI = "ftp://ftp.cac.washington.edu/imap/imap-${PV}.tar.gz \
           file://quote_cctype.patch"

SRC_URI[md5sum] = "7903800dc2604000016de070e0c55840"
SRC_URI[sha256sum] = "4b1137b87249782496ec3eeacaf83bbf09312a7d2ae3aead262179041b55565f"

S = "${WORKDIR}/imap-${PV}"

EXTRA_OEMAKE = "CC='${CC}'"

HEADERS = "src/c-client/*.h src/osdep/unix/*.h c-client/auths.c c-client/linkage.c c-client/linkage.h c-client/osdep.h"

do_compile() {
    echo "SSLINCLUDE=${STAGING_INCDIR} SSLLIB=${STAGING_LIBDIR}" > ${S}/SPECIALS
    oe_runmake lnp
}

do_install() {
    install -d ${D}${includedir}/c-client
    install ${HEADERS} ${D}${includedir}/c-client
    install -d ${D}${libdir}
    install c-client/c-client.a ${D}${libdir}/libc-client.a
}

ALLOW_EMPTY_${PN} = "1"
