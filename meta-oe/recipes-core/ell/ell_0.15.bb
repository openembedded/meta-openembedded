SUMMARY  = "Embedded Linux Library"
DESCRIPTION = "ELL is a DBUS library which provides DBUS bindings."
LICENSE  = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fb504b67c50331fc78734fed90fb0e09"
SECTION = "libs"

inherit autotools pkgconfig

S = "${WORKDIR}/git"
SRCREV = "ea0f5f42e3af3fad2b60ee87719cdd227fd89ab0"
SRC_URI = "git://git.kernel.org/pub/scm/libs/ell/ell.git"

do_configure_prepend () {
    mkdir -p ${S}/build-aux
}

DEPENDS = "dbus"
