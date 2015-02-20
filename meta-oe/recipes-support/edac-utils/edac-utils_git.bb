SUMMARY = "Userspace helper for Linux kernel EDAC drivers"
HOMEPAGE = "https://github.com/grondo/edac-utils"
SECTION = "Applications/System"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;beginline=1;endline=2;md5=1fbd81241fe252ec0f5658a521ab7dd8"

DEPENDS = " sysfsutils"

SRCREV = "f9aa96205f610de39a79ff43c7478b7ef02e3138"
PV = "0.16+git${SRCPV}"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/grondo/edac-utils;protocol=http"

inherit autotools-brokensep

do_configure_prepend () {
    touch ${S}/ChangeLog
    ${S}/bootstrap
}

COMPATIBLE_HOST = '(x86_64.*|i.86.*)-linux'

RDEPENDS_${PN} = " perl"
