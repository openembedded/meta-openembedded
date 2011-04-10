#
# Copyright Matthias Hentges <devel@hentges.net> (c) 2006
# License: MIT (see http://www.opensource.org/licenses/mit-license.php for a copy of the license)
#
# Filename: fbset_2.1.bb
# Date: 28-May-06

DESCRIPTION = "The fbset console tool"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://fbset.c;endline=19;md5=bf326f82cdfcac391af208f019c5603f"
RRECOMMENDS_${PN} = "fbset-modes"
DEPENDS = "bison-native"

PR = "r4"

SRC_URI = "http://ftp.debian.org/debian/pool/main/f/fbset/fbset_2.1.orig.tar.gz \
           file://makefile.patch"

inherit autotools update-alternatives

PARALLEL_MAKE = ""

do_install() {
        install -d ${D}${sbindir} ${D}${datadir}/man/man8 ${D}${datadir}/man/man5
        install -m 0755 ${WORKDIR}/${P}/fbset ${D}${sbindir}/fbset.real
        install -m 0644 ${WORKDIR}/${P}/*.5 ${D}${datadir}/man/man5
        install -m 0644 ${WORKDIR}/${P}/*.8 ${D}${datadir}/man/man8
}

ALTERNATIVE_NAME = "fbset"
ALTERNATIVE_LINK = "${sbindir}/${ALTERNATIVE_NAME}"
ALTERNATIVE_PATH = "${sbindir}/fbset.real"
ALTERNATIVE_PRIORITY = "55"



SRC_URI[md5sum] = "40ed9608f46d787bfb65fd1269f7f459"
SRC_URI[sha256sum] = "517fa062d7b2d367f931a1c6ebb2bef84907077f0ce3f0c899e34490bbea9338"
