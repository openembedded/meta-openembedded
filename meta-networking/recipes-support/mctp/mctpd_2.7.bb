require mctp.inc

SUMMARY = "Management Component Transport Protocol (MCTP) control protocol daemon"

inherit systemd features_check useradd

MESON_INSTALL_TAGS += "mctpd"

do_install:append () {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/conf/mctpd.service \
            ${D}${systemd_system_unitdir}/mctpd.service
    install -m 0644 ${S}/conf/*.target \
            ${D}${systemd_system_unitdir}/
    install -d ${D}${datadir}/dbus-1/system.d
    install -m 0644 ${S}/conf/mctpd-dbus.conf \
            ${D}${datadir}/dbus-1/system.d/mctpd.conf
    install -d ${D}${nonarch_libdir}/sysusers.d
    printf 'u! mctpd - "mctpd daemon" / /bin/false\ng mctp-admin\n' > ${D}${nonarch_libdir}/sysusers.d/mctpd.conf
}
FILES:${PN} += "${datadir}/dbus-1/system.d/mctpd.conf ${nonarch_libdir}/sysusers.d/mctpd.conf"

SYSTEMD_SERVICE:${PN} = "mctpd.service mctp.target mctp-local.target"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "-r -U -d / -s /bin/false mctpd"
GROUPADD_PARAM:${PN} = "-r mctp-admin"

DEPENDS += "systemd"
REQUIRED_DISTRO_FEATURES = "systemd"
