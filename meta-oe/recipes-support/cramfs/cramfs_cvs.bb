DESCRIPTION = "Builds cramfs filesystems for embedded systems"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/linux/COPYING;md5=9579385572eb40eded61dcb07e0038a4"
DEPENDS = "zlib"
SRCDATE = "20110110"
PV = "1.1+cvs${SRCDATE}"

SRC_URI = "cvs://anonymous@cramfs.cvs.sourceforge.net/cvsroot/cramfs;module=linux"
S = "${WORKDIR}/linux/scripts/cramfs"

EXTRA_OEMAKE = "\
    'CC=${CC}' \
    'CFLAGS=${CFLAGS}' \
    'LDFLAGS=${LDFLAGS}' \
"


do_compile_prepend() {
        ln -s GNUmakefile Makefile
}

do_install() {
	install -d ${D}${bindir}
	install mkcramfs ${D}${bindir}
	install cramfsck ${D}${bindir}
}

BBCLASSEXTEND = "native"

