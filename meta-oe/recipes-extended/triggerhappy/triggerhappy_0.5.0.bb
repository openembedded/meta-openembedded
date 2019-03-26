SUMMARY = "A lightweight hotkey daemon"
HOMEPAGE = "https://github.com/wertarbyte/triggerhappy"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://github.com/wertarbyte/triggerhappy/archive/debian/0.5.0-1.tar.gz"

SRC_URI[md5sum] = "d7e78a0c0e0d5d786111ee92a0fbcf81"
SRC_URI[sha256sum] = "36e59d16562f66308158208d46f2b27017fa1947a47266110b3ed7e0789d0a35"

S = "${WORKDIR}/${PN}-debian"

inherit autotools-brokensep update-rc.d systemd

DEPENDS = "pkgconfig-native"
PACKAGECONFIG = "${@bb.utils.contains('DISTRO_FEATURES','systemd','systemd','',d)}"
PACKAGECONFIG[systemd] = ",,systemd"

INITSCRIPT_NAME = "triggerhappy"
INITSCRIPT_PARAMS = "defaults"
SYSTEMD_SERVICE_${PN} = "triggerhappy.service triggerhappy.socket"

FILES_${PN} = "\
${sbindir}/thd \
${sbindir}/th-cmd \
${sysconfdir}/triggerhappy/triggers.d \
${nonarch_base_libdir}/udev/rules.d/80-triggerhappy.rules \
${sysconfdir}/init.d/triggerhappy \
${systemd_unitdir}/system \
"
CONFFILES_${PN} = "${sysconfdir}/udev/rules.d/80-triggerhappy.rules"

do_install_append() {
    install -d ${D}${sysconfdir}/triggerhappy/triggers.d

    install -d ${D}${nonarch_base_libdir}/udev/rules.d
    install -m 0644 ${S}/udev/triggerhappy-udev.rules ${D}${nonarch_base_libdir}/udev/rules.d/80-triggerhappy.rules

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/debian/init.d ${D}${sysconfdir}/init.d/triggerhappy

    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${S}/systemd/triggerhappy.socket ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/systemd/triggerhappy.service ${D}${systemd_unitdir}/system
}
