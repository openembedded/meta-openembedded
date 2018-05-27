SUMMARY = "ZeroTier creates a global provider-independent virtual private cloud network"
DESCRIPTION = "Create flat virtual Ethernet networks of almost unlimited size"
HOMEPAGE = "https://www.zerotier.com"
SECTION = "net"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=886557d0c9eee76bfbb292c1e01e2f43"
DEPENDS = ""

SRC_URI = "https://github.com/zerotier/ZeroTierOne/archive/1.2.10.tar.gz \
		file://0001-remove-pie-and-strip-and-allow-cflags.patch \
		file://zerotier"

SRC_URI[md5sum] = "b5454b93bfc67439839c334756788357"
SRC_URI[sha256sum] = "1c79ec57e67764079a77704b336e642ae3cf221dc8088b0cf9e9c81e0a9c0c57"

S = "${WORKDIR}/ZeroTierOne-${PV}"

INSANE_SKIP_${PN} = "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

RDEPENDS_${PN} = "kernel-module-tun"

inherit update-rc.d systemd

INITSCRIPT_NAME = "zerotier"
INITSCRIPT_PARAMS = "defaults 11"

do_configure () {
}

do_compile () {
    oe_runmake all
}

do_install() {
    install -d ${D}${sbindir}
    install -m 755 zerotier-one ${D}${sbindir}
    ln zerotier-one ${D}${sbindir}/zerotier-cli
    ln zerotier-one ${D}${sbindir}/zerotier-idtool
    chown -R root:root ${D}${sbindir}/zerotier-cli
    install -d ${D}/var/lib/zerotier-one
    ln -s ../../..${sbindir}/zerotier-one ${D}/var/lib/zerotier-one/zerotier-one
    ln -s ../../..${sbindir}/zerotier-one ${D}/var/lib/zerotier-one/zerotier-cli
    ln -s ../../..${sbindir}/zerotier-one ${D}/var/lib/zerotier-one/zerotier-idtool
    install -d ${D}${sysconfdir}/init.d/
    install -m 755 ../zerotier ${D}${sysconfdir}/init.d/
}
