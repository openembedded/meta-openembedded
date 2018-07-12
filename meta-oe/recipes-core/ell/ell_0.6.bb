SUMMARY  = "Embedded Linux Library"
DESCRIPTION = "ELL is a DBUS library which provides DBUS bindings."
LICENSE  = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fb504b67c50331fc78734fed90fb0e09"
SECTION = "libs"

inherit autotools pkgconfig

S = "${WORKDIR}/git"
SRCREV = "59ff3160fe55a841e662f8776f0520f2038235f4"
SRC_URI = " \
    git://git.kernel.org/pub/scm/libs/ell/ell.git \
    file://0001-dhcp-include-if_arp.h-from-libc-instead-of-linux-hea.patch \
"

do_configure_prepend () {
    mkdir ${S}/build-aux
}

DEPENDS = "dbus"
