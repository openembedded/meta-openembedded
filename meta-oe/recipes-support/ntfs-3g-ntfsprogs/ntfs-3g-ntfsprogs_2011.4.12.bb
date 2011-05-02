DESCRIPTION = "The NTFS-3G driver is an open source, freely available NTFS driver for Linux with read and write support."
HOMEPAGE = "http://www.ntfs-3g.org/"
DEPENDS = "fuse libgcrypt"
PROVIDES = "ntfsprogs ntfs-3g"
LICENSE = "GPLv2 & LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552 \
                    file://COPYING.LIB;md5=f30a9716ef3762e3467a2f62bf790f0a"
PR = "r0"

SRC_URI = "http://tuxera.com/opensource/ntfs-3g_ntfsprogs-${PV}.tgz"
S = "${WORKDIR}/ntfs-3g_ntfsprogs-${PV}"

SRC_URI[md5sum] = "9c4ce318373b15332239a77a9d2a39fe"
SRC_URI[sha256sum] = "aa8c747f0bfe819b2387b05e12db8d35e4ac96dcb2432873a0b939e248b2ee05"

inherit autotools

# required or it calls ldconfig at install step
EXTRA_OEMAKE = "LDCONFIG=echo"

PACKAGES =+ "ntfs-3g ntfsprogs libntfs-3g"

FILES_ntfs-3g = "${base_sbindir}/*.ntfs-3g ${bindir}/ntfs-3g*"
RDEPENDS_ntfs-3g += "fuse"

FILES_ntfsprogs = "${base_sbindir}/* ${bindir}/* ${sbindir}/*"
FILES_libntfs-3g = "${libdir}/*${SOLIBS}"
