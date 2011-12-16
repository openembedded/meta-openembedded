DESCRIPTION = "Tools for ethernet bridging."
HOMEPAGE = "http://bridge.sourceforge.net/"
SECTION = "console/network"
LICENSE = "GPLv2"

SRC_URI = "${SOURCEFORGE_MIRROR}/bridge/bridge-utils-${PV}.tar.gz"

inherit autotools

EXTRA_OECONF = "--with-linux-headers=${STAGING_INCDIR}"

do_install_append () {
	mv ${D}${sbindir}/brctl ${D}${sbindir}/brctl.${PN}
	install -d ${D}/${datadir}/bridge-utils
	install -d ${D}/${sysconfdir}/network/if-pre-up.d
	install -d ${D}/${sysconfdir}/network/if-post-down.d
}

DEPENDS = "sysfsutils"
RRECOMMENDS_${PN} = "kernel-module-bridge"

pkg_postinst_${PN} () {
	update-alternatives --install ${sbindir}/brctl brctl brctl.${PN} 100
}

pkg_prerm_${PN} () {
	update-alternatives --remove brctl brctl.${PN}
}
