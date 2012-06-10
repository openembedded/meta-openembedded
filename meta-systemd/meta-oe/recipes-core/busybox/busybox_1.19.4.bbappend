inherit systemd

PRINC := "${@int(PRINC) + 1}"

# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://busybox-syslog.service.in \
            file://busybox-klogd.service.in"

SYSTEMD_PACKAGES = "${PN}-syslog-systemd"
SYSTEMD_SERVICE_${PN}-syslog-systemd = "busybox-syslog.service"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	sed 's,@base_sbindir@,${base_sbindir},g' < ${WORKDIR}/busybox-syslog.service.in \
	     > ${D}${systemd_unitdir}/system/busybox-syslog.service
	sed 's,@base_sbindir@,${base_sbindir},g' < ${WORKDIR}/busybox-klogd.service.in \
	     > ${D}${systemd_unitdir}/system/busybox-klogd.service

	ln -sf /dev/null ${D}${systemd_unitdir}/system/syslog.service
}
FILES_${PN}-syslog-systemd = "${systemd_unitdir}/system/syslog.service"
