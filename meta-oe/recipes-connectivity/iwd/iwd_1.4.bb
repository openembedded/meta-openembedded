DESCRIPTION = "Wireless daemon for Linux"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fb504b67c50331fc78734fed90fb0e09"

inherit autotools pkgconfig systemd python3native

DEPENDS = "ell"

SRC_URI = "git://git.kernel.org/pub/scm/network/wireless/iwd.git \
           file://0001-Makefile.am-Avoid-redirection-of-input-and-output-fi.patch \
           file://0001-build-Support-missing-rawmemchr.patch \
          "
SRCREV = "860fa4697f349da7791ecf22ca76f9ac0e5de261"
S = "${WORKDIR}/git"

PACKAGECONFIG ??= " \
    client \
    monitor \
    manual-pages \
    ${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)} \
"
PACKAGECONFIG[client] = "--enable-client,--disable-client,readline"
PACKAGECONFIG[monitor] = "--enable-monitor,--disable-monitor"
PACKAGECONFIG[manual-pages] = "--enable-manual-pages,--disable-manual-pages,python3-docutils-native"
PACKAGECONFIG[wired] = "--enable-wired,--disable-wired"
PACKAGECONFIG[ofono] = "--enable-ofono,--disable-ofono"
PACKAGECONFIG[systemd] = "--with-systemd-unitdir=${systemd_system_unitdir},--disable-systemd-service,systemd"

EXTRA_OECONF += "--enable-external-ell"

do_configure_prepend () {
    mkdir -p ${S}/build-aux
}

do_install_append() {
    mkdir --parents ${D}${docdir}/${BPN}
    install -m644 ${S}/doc/*.txt ${D}${docdir}/${BPN}
    # If client and monitor are disabled, bindir is empty, causing a QA error
    rmdir --ignore-fail-on-non-empty ${D}/${bindir}
}

FILES_${PN} += "${datadir}/dbus-1 ${nonarch_libdir}/modules-load.d ${systemd_unitdir}/network/"

SYSTEMD_SERVICE_${PN} = "iwd.service ${@bb.utils.contains('PACKAGECONFIG', 'wired', 'ead.service', '', d)}"

RDEPENDS_${PN} = "dbus"

RRECOMMENDS_${PN} = "\
    kernel-module-pkcs7-message \
    kernel-module-pkcs8-key-parser \
    kernel-module-x509-key-parser \
"
