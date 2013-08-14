SUMMARY = "utilities to create, check, label and dump exFAT filesystem"
DESCRIPTION = "Utilities to manage extended file allocation table filesystem. \
This package provides tools to create, check and label the filesystem. It \
contains \
 - dumpexfat to dump properties of the filesystem \
 - exfatfsck / fsck.exfat to report errors found on a exFAT filesystem \
 - exfatlabel to label a exFAT filesystem \
 - mkexfatfs / mkfs.exfat to create a exFAT filesystem. \
"
HOMEPAGE = "http://code.google.com/p/exfat/"
SECTION = "universe/otherosfs"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
SRC_URI = "${DEBIAN_MIRROR}/main/e/exfat-utils/exfat-utils_${PV}.orig.tar.gz \
"
DEPENDS = "virtual/libc"

inherit scons

SRC_URI[md5sum] = "e592130829d0bf61fa5e3cd1c759d329"
SRC_URI[sha256sum] = "eeacedca1878065dc3886674ae39cd51149c37bd7d6d7e9325c971a1d1acdab3"

EXTRA_OESCONS = " \
    DESTDIR=${D}/${base_sbindir} \
"

do_install_prepend() {
    install -d ${D}/${base_sbindir}
}
