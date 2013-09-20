SUMMARY = "read and write exFAT driver for FUSE"
DESCRIPTION = "fuse-exfat is a read and write driver implementing the \
extended file allocation table as a filesystem in userspace. A mounthelper \
is provided unter the name mount.exfat-fuse. \
"
HOMEPAGE = "http://code.google.com/p/exfat/"
SECTION = "universe/otherosfs"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
SRC_URI = "${DEBIAN_MIRROR}/main/f/fuse-exfat/fuse-exfat_${PV}.orig.tar.gz \
"
DEPENDS = "fuse virtual/libc"
RRECOMMENDS_${PN} = "util-linux-mount"

inherit scons

SRC_URI[md5sum] = "7988a5111841593231f20af22153362d"
SRC_URI[sha256sum] = "12ac1ba1b7d4343bef64e7898176705a41cfe3b5a7a179e28549d242e2854758"

EXTRA_OESCONS = " \
    DESTDIR=${D}/${base_sbindir} \
"

do_install_prepend() {
    install -d ${D}/${base_sbindir}
}
