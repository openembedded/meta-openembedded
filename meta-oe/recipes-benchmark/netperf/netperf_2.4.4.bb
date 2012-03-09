DESCRIPTION="Network performance benchmark including tests for TCP, UDP, sockets, ATM and more."
SECTION = "console/network"
HOMEPAGE = "http://www.netperf.org/"
LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://COPYING;md5=a0ab17253e7a3f318da85382c7d5d5d6"

SRC_URI="ftp://ftp.netperf.org/netperf/archive/netperf-${PV}.tar.bz2 \
         file://cpu_set.patch \
         file://vfork.patch \
         file://init"

SRC_URI[md5sum] = "0e942f22864e601406a994420231075b"
SRC_URI[sha256sum] = "28e76af491ea3696885e4558ae2f5628a4b9ebdbefc2f1d9cf1b35db2813e497"

inherit update-rc.d autotools

S = "${WORKDIR}/netperf-${PV}"

# cpu_set.patch plus _GNU_SOURCE makes src/netlib.c compile with CPU_ macros
CFLAGS_append = " -DDO_UNIX -DDO_IPV6 -D_GNU_SOURCE"

do_install() {
        install -d ${D}${sbindir} ${D}${bindir} ${D}${sysconfdir}/init.d
        install -m 4755 src/netperf ${D}${bindir}
        install -m 4755 src/netserver ${D}${sbindir}
        install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/netperf

        # man
        install -d ${D}${mandir}/man1/
        install -m 0644 doc/netserver.man ${D}${mandir}/man1/netserver.1
        install -m 0644 doc/netperf.man ${D}${mandir}/man1/netperf.1

        # move scripts to examples directory
        install -d ${D}${docdir}/netperf/examples
        install -m 0644 doc/examples/*_script ${D}${docdir}/netperf/examples/

        # docs ..
        install -m 0644 COPYING ${D}${docdir}/netperf
        install -m 0644 Release_Notes ${D}${docdir}/netperf
        install -m 0644 README ${D}${docdir}/netperf
        install -m 0644 doc/netperf_old.ps ${D}${docdir}/netperf
}

INITSCRIPT_NAME="netperf"
INITSCRIPT_PARAMS="defaults"
