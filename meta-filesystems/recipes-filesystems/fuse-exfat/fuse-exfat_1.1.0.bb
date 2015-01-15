SUMMARY = "read and write exFAT driver for FUSE"
DESCRIPTION = "fuse-exfat is a read and write driver implementing the \
extended file allocation table as a filesystem in userspace. A mounthelper \
is provided under the name mount.exfat-fuse. \
"
HOMEPAGE = "http://code.google.com/p/exfat/"
SECTION = "universe/otherosfs"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
SRC_URI = "${DEBIAN_MIRROR}/main/f/fuse-exfat/fuse-exfat_${PV}.orig.tar.gz \
"
DEPENDS = "fuse virtual/libc"
RRECOMMENDS_${PN} = "util-linux-mount"

inherit scons

SRC_URI[md5sum] = "b2a23c032661cb1c1da4514e7af33916"
SRC_URI[sha256sum] = "198c520e417e955dc5c08687c278e63eefa56719da4452aa4a605be0327f953e"

EXTRA_OESCONS = " \
    CCFLAGS='${CCFLAGS} -std=c99' DESTDIR=${D}/${base_sbindir} \
"

do_install_prepend() {
    install -d ${D}/${base_sbindir}
}
