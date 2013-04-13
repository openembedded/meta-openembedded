SUMMARY = "A new modern implementation of traceroute(8) utility for Linux systems"
DESCRIPTION = "The traceroute utility displays the route used by IP packets on \
their way to a specified network (or Internet) host.  Traceroute displays \
the IP number and host name (if possible) of the machines along the \
route taken by the packets.  Traceroute is used as a network debugging \
tool.  If you're having network connectivity problems, traceroute will \
show you where the trouble is coming from along the route."
SECTION = "console/network"
HOMEPAGE = "http://traceroute.sourceforge.net/"
LICENSE = "GPL-2.0 LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://COPYING.LIB;md5=bbb461211a33b134d42ed5ee802b37ff"

PR = "r1"

inherit update-alternatives

SRC_URI = "${SOURCEFORGE_MIRROR}/traceroute/traceroute/traceroute-2.0.18/traceroute-2.0.18.tar.gz \
"

SRC_URI[md5sum] = "b7254149b7f081cce07f4b9e065ba5ef"
SRC_URI[sha256sum] = "5994a88520927fefe3c9754aaf1e02b4d0f3f8fb1f521a68fa86215c3fcab9ef"

do_compile() {
    export LDFLAGS="${TARGET_LDFLAGS} -L${S}/libsupp"
    oe_runmake "env=yes"
}

do_install() {
    install -d ${D}${bindir}
    install -m755 ${BPN}/${BPN} ${D}${bindir}

    install -m755 wrappers/tcptraceroute ${D}${bindir}

    install -d ${D}${mandir}
    install -p -m644 ${BPN}/${BPN}.8 ${D}${mandir}
    ln -s ${BPN}.8 ${D}${mandir}/${BPN}6.8
    ln -s ${BPN}.8 ${D}${mandir}/tcptraceroute.8

}

ALTERNATIVE_${PN} = "traceroute6"
ALTERNATIVE_LINK_NAME[traceroute6] = "${bindir}/traceroute"
