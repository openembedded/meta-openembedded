SUMMARY = "USB Gadget neXt Configfs Library"
LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LGPL;md5=4fbd65380cdd255951079008b364516c"

inherit autotools pkgconfig systemd update-rc.d update-alternatives

PV = "0.2.0+git${SRCPV}"
SRCREV = "45c14ef4d5d7ced0fbf984208de44ced6d5ed898"
SRCBRANCH = "master"
SRC_URI = " \
    git://github.com/libusbgx/libusbgx.git;branch=${SRCBRANCH};protocol=https \
    file://gadget-start \
    file://usbgx.initd \
    file://usbgx.service \
"

S = "${WORKDIR}/git"

PACKAGECONFIG ??= "examples gadget-schemes libconfig"
PACKAGECONFIG[libconfig] = "--with-libconfig=yes,--without-libconfig,libconfig"
PACKAGECONFIG[examples] = "--enable-examples,--disable-examples"
PACKAGECONFIG[gadget-schemes] = "--enable-gadget-schemes,--disable-gadget-schemes"
PACKAGECONFIG[tests] = "--enable-tests,--disable-tests,cmocka"

PACKAGE_BEFORE_PN = "${@bb.utils.contains('PACKAGECONFIG', 'examples', '${PN}-examples', '', d)}"

SYSTEMD_PACKAGES = "${PN}-examples"
SYSTEMD_SERVICE:${PN}-examples = "usbgx.service"
SYSTEMD_AUTO_ENABLE:${PN}-examples = "${@bb.utils.contains('PACKAGECONFIG', 'examples', 'enable', 'disable', d)}"

INITSCRIPT_PACKAGES = "${@bb.utils.contains('PACKAGECONFIG', 'examples', '${PN}-examples', '', d)}"
INITSCRIPT_NAME = "usbgx"
INITSCRIPT_PARAMS = "defaults"
INHIBIT_UPDATERCD_BBCLASS = "${@bb.utils.contains('PACKAGECONFIG', 'examples', '1', '0', d)}"

do_install:append() {
    install -Dm 0755 ${WORKDIR}/gadget-start ${D}/${bindir}/gadget-start
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -Dm 0644 ${WORKDIR}/usbgx.service ${D}${systemd_system_unitdir}/usbgx.service
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -Dm 0755 ${WORKDIR}/usbgx.initd ${D}${sysconfdir}/init.d/usbgx
    fi
}

FILES:${PN}-examples = "${bindir}/* ${sysconfdir}/*"
RDEPENDS:${PN}-examples += "${@bb.utils.contains('PACKAGECONFIG', 'examples', 'libusbgx-config', '', d)}"

ALTERNATIVE_PRIORITY = "90"
ALTERNATIVE:${PN}-examples = "${@bb.utils.contains('PACKAGECONFIG', 'examples', 'gadget-acm-ecm show-gadgets', '', d)}"
ALTERNATIVE_LINK_NAME[gadget-acm-ecm] = "${bindir}/gadget-acm-ecm"
ALTERNATIVE_LINK_NAME[show-gadgets] = "${bindir}/show-gadgets"
