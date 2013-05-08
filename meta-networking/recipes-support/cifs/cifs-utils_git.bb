DESCRIPTION = "A a package of utilities for doing and managing mounts of the Linux CIFS filesystem."
HOMEPAGE = "http://wiki.samba.org/index.php/LinuxCIFS_utils"

LICENSE = "GPLv3 & LGPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PV = "5.9"
PR = "r1"

SRCREV = "353d491dcb5d69d31434abeb962c8e9a49c36867"
SRC_URI = "git://git.samba.org/cifs-utils.git"

S = "${WORKDIR}/git"

inherit autotools

do_install_append() {
    # Remove empty /usr/bin and /usr/sbin directories since the mount helper
    # is installed to /sbin
    rmdir ${D}${bindir} ${D}${sbindir}
}

RRECOMMENDS_${PN} = "kernel-module-cifs"
