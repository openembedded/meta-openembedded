SUMMARY = "tcpslice"
DESCRIPTION = "A tool for extracting parts of a tcpdump packet trace."
HOMEPAGE = "http://www.tcpdump.org/related.html"
SECTION = "net"

LICENSE = "BSD-4-Clause-Shortened"
LIC_FILES_CHKSUM = "file://tcpslice.c;endline=8;md5=581f80110b7551334693070de9a1f9a8"

SRC_URI = "http://www.tcpdump.org/release/${BP}.tar.gz \
           "
SRC_URI[sha256sum] = "3d8c9964ecd3cf98310584f67de5530eb449e7a6bf4613599170ee102bbdd29f"

UPSTREAM_CHECK_REGEX = "tcpslice-(?P<pver>\d+(\.\d+)+)\.tar"

inherit autotools-brokensep pkgconfig

DEPENDS = "libpcap"

EXTRA_AUTORECONF += "--exclude=aclocal"

do_install () {
    install -d ${D}${sbindir}
    install -m 0755 tcpslice ${D}${sbindir}
}
