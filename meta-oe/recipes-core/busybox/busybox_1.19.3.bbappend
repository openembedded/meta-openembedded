inherit systemd

PRINC := "${@int(PRINC) + 2}"

# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://busybox-syslog.service.in \
            file://busybox-klogd.service.in \
            file://busybox-syslog.default"

SYSTEMD_PACKAGES = "${PN}-syslog-systemd"
SYSTEMD_SERVICE_${PN}-syslog-systemd = "busybox-syslog.service"

do_install_append() {
	install -d ${D}${sysconfdir}/default
	install -m 0644 ${WORKDIR}/busybox-syslog.default ${D}${sysconfdir}/default/busybox-syslog

	install -d ${D}${base_libdir}/systemd/system
	sed 's,@base_sbindir@,${base_sbindir},g' < ${WORKDIR}/busybox-syslog.service.in \
	     > ${D}${base_libdir}/systemd/system/busybox-syslog.service
	sed 's,@base_sbindir@,${base_sbindir},g' < ${WORKDIR}/busybox-klogd.service.in \
	     > ${D}${base_libdir}/systemd/system/busybox-klogd.service

	ln -sf /dev/null ${D}${base_libdir}/systemd/system/syslog.service
}

PACKAGES += "${PN}-syslog-systemd"

RDEPENDS_${PN}-syslog-systemd += "${PN}-syslog"
FILES_${PN}-syslog-systemd = "${base_libdir}/systemd"
