inherit systemd

PRINC := "${@int(PRINC) + 1}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SYSTEMD_PACKAGES = "${PN}-systemd ntpdate-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "ntpd.service"
SYSTEMD_SERVICE_ntpdate-systemd = "ntpdate.service"

SRC_URI += " \
	file://ntpdate.service \
        file://ntpd.service \
"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/ntpdate.service ${D}${systemd_unitdir}/system/
	install -m 0644 ${WORKDIR}/ntpd.service ${D}${systemd_unitdir}/system/
}
