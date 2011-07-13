LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "libxml-simple-perl-native"

PR = "r3"

SRC_URI = "http://tango.freedesktop.org/releases/icon-naming-utils-${PV}.tar.gz"
SRC_URI[md5sum] = "2c5c7a418e5eb3268f65e21993277fba"
SRC_URI[sha256sum] = "044ab2199ed8c6a55ce36fd4fcd8b8021a5e21f5bab028c0a7cdcf52a5902e1c"

S = "${WORKDIR}/icon-naming-utils-${PV}"

inherit autotools allarch perlnative

do_configure_append() {
        # replace paths to STAGING_BINDIR_NATIVE/perl with ${bindir}/perl
        sed -i -e "1s:#!.*:#! /usr/bin/env perl:" ${S}/icon-name-mapping.pl.in
}

FILES_${PN} += "${datadir}/dtds"

BBCLASSEXTEND = "native"
