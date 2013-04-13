PRINC := "${@int(PRINC) + 1}"

# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://busybox-syslog.default"

do_install_append() {
    install -d ${D}${sysconfdir}/default
    install -m 0644 ${WORKDIR}/busybox-syslog.default ${D}${sysconfdir}/default/busybox-syslog
}

FILES_${PN}-syslog += "${sysconfdir}/default/busybox-syslog"
