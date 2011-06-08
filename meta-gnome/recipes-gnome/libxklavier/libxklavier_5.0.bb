DESCRIPTION = "Helper lib for keyboard management"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=6e29c688d912da12b66b73e32b03d812"

DEPENDS = "xkbcomp gtk+ iso-codes "

inherit gnome

SRC_URI[archive.md5sum] = "134c7ea177ff901123b909de77394780"
SRC_URI[archive.sha256sum] = "dfd94b17a752b2839281764f0a16af2b338024c8867c10fba7015f276dec4db0"

EXTRA_OECONF = "--with-xkb-bin-base=${bindir}"

do_configure_append() {
        find ${S} -name Makefile | xargs sed -i s:'-I$(includedir)':'-I.':g
        find ${S} -name Makefile | xargs sed -i s:'-I/usr/include':'-I${STAGING_INCDIR}':g
}

do_compile_append() {
        sed -i -e s:${STAGING_DIR_TARGET}::g \
               -e s:/${TARGET_SYS}::g \
                  libxklavier.pc
}


