SUMMARY = "A privileged helper for utmp/wtmp updates"
DESCRIPTION = "\
This library provides interface for terminal emulators such as \
screen and xterm to record user sessions to utmp and wtmp files."
HOMEPAGE = "ftp://ftp.altlinux.org/pub/people/ldv/utempter"
SECTION = "System Environment/Libraries"
LICENSE = "GPLv2 GPLv2+ LGPLv2 MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

SRC_URI = "ftp://ftp.altlinux.org/pub/people/ldv/utempter/${BP}.tar.bz2"
SRC_URI[md5sum] = "b43827806923903aba2bc7cd3a2d45b7"
SRC_URI[sha256sum] = "b898565f31ced7e5c1fa0a2eaa0f6ff0ed862b5fe375d26375b64bfbdfeac397"

CFLAGS += "-DLIBEXECDIR=$(libexecdir)"

do_compile() {
    oe_runmake                      \
        libdir=${libdir}            \
        libexecdir=${libexecdir}    \
}

do_install() {
    oe_runmake install              \
        DESTDIR=${D}                \
        libdir="${libdir}"          \
        libexecdir="${libexecdir}"  \
        includedir=${includedir}    \
        mandir=${mandir}

        rm -f ${D}${libdir}/*.a
}

FILES_${PN} = "${libdir}/*.so.*"
FILES_${PN} += "${libexecdir}/utempter/utempter"

FILES_${PN}-dbg += "${libexecdir}/utempter/.debug/utempter"
