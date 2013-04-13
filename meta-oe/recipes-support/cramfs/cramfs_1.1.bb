DESCRIPTION = "Builds cramfs filesystems for embedded systems"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"
DEPENDS = "zlib"

PE = "1"

SRC_URI = "http://sourceforge.net/projects/cramfs/files/cramfs/1.1/${PN}-${PV}.tar.gz"

SRC_URI[md5sum] = "d3912b9f7bf745fbfea68f6a9b9de30f"
SRC_URI[sha256sum] = "133caca2c4e7c64106555154ee0ff693f5cf5beb9421ce2eb86baee997d22368"

EXTRA_OEMAKE = "\
    'CC=${CC}' \
    'CFLAGS=${CFLAGS}' \
    'LDFLAGS=${LDFLAGS}' \
"

do_compile_prepend() {
    ln -sf GNUmakefile Makefile
}

do_install() {
    install -d ${D}${bindir}
    install mkcramfs ${D}${bindir}
    install cramfsck ${D}${bindir}
}

BBCLASSEXTEND = "native"
