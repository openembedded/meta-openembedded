DESCRIPTION = "A user-mode PPPoE client and server suite for Linux"
HOMEPAGE = "http://www.roaringpenguin.com/products/pppoe"
SECTION = "console/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://doc/LICENSE;md5=a194eaefae2be54ee3221339b10d0581"
RDEPENDS_${PN} = "ppp"
RDEPENDS_${PN}-server = "${PN}"
RRECOMMENDS_${PN} = "ppp-oe"
PR = "r8"

SRC_URI = "http://www.roaringpenguin.com/files/download/${P}.tar.gz \
           file://top-autoconf.patch \
           file://configure_in_cross.patch \
           file://pppoe-src-restrictions.patch \
           file://update-config.patch \
           file://dont-swallow-errors.patch \
           file://use-ldflags.patch \
           file://pppoe-server.default \
           file://pppoe-server.init"

inherit autotools update-rc.d

do_install() {
        # Install init script and default settings
        install -m 0755 -d ${D}${sysconfdir}/default ${D}${sysconfdir}/init.d
        install -m 0644 ${WORKDIR}/pppoe-server.default ${D}${sysconfdir}/default/pppoe-server
        install -m 0755 ${WORKDIR}/pppoe-server.init ${D}${sysconfdir}/init.d/pppoe-server
        # Install
        oe_runmake -C ${S} RPM_INSTALL_ROOT=${D} docdir=${docdir} install
}

# Insert server package before main package
PACKAGES = "${PN}-dbg ${PN}-server ${PN}-relay ${PN}-sniff ${PN} ${PN}-doc"

FILES_${PN}-server = "${sysconfdir}/default/pppoe-server \
                      ${sysconfdir}/init.d/pppoe-server \
                      ${sbindir}/pppoe-server \
                      ${sysconfdir}/ppp/pppoe-server-options"
FILES_${PN}-relay = "${sbindir}/pppoe-relay"
FILES_${PN}-sniff = "${sbindir}/pppoe-sniff"

pkg_postinst_${PN} () {
    if [ x"$D" != "x" ]; then
        exit 1
    fi
    chmod 4755 ${sbindir}/pppoe
}

CONFFILES_${PN} = "${sysconfdir}/ppp/pppoe.conf \
                   ${sysconfdir}/ppp/firewall-standalone \
                   ${sysconfdir}/ppp/firewall-masq"
CONFFILES_${PN}-server = "${sysconfdir}/ppp/pppoe-server-options \
                          ${sysconfdir}/default/pppoe-server"

INITSCRIPT_PACKAGES            = "${PN}-server"
INITSCRIPT_NAME_${PN}-server   = "pppoe-server"
INITSCRIPT_PARAMS_${PN}-server = "defaults 92 8"


SRC_URI[md5sum] = "0e32760f498f9cde44081ee6aafc823b"
SRC_URI[sha256sum] = "d916e9cfe1e62395f63a5361936fa855f6d0f0a37dc7227b394cdb725f553479"
