inherit systemd

PRINC := "${@int(PRINC) + 3}"

# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://busybox-syslog.service.in \
            file://busybox-klogd.service.in"

SYSTEMD_PACKAGES = "${PN}-syslog"
SYSTEMD_SERVICE_${PN}-syslog = "busybox-syslog.service"

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    sed 's,@base_sbindir@,${base_sbindir},g' < ${WORKDIR}/busybox-syslog.service.in \
         > ${D}${systemd_unitdir}/system/busybox-syslog.service
    sed 's,@base_sbindir@,${base_sbindir},g' < ${WORKDIR}/busybox-klogd.service.in \
         > ${D}${systemd_unitdir}/system/busybox-klogd.service

    ln -sf /dev/null ${D}${systemd_unitdir}/system/syslog.service
}
FILES_${PN}-syslog += "${systemd_unitdir}/system/syslog.service"

RPROVIDES_${PN}-syslog += "${PN}-syslog-systemd"
RREPLACES_${PN}-syslog += "${PN}-syslog-systemd"
RCONFLICTS_${PN}-syslog += "${PN}-syslog-systemd"
