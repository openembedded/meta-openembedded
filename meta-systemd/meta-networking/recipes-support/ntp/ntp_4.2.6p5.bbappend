inherit systemd

PRINC := "${@int(PRINC) + 1}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SYSTEMD_PACKAGES = "${PN} ntpdate"
SYSTEMD_SERVICE_${PN} = "ntpd.service"
SYSTEMD_SERVICE_ntpdate = "ntpdate.service"
RPROVIDES_${PN} += "${PN}-systemd"
RPROVIDES_ntpdate += "ntpdate-systemd"

FILES_ntpdate += "${systemd_unitdir}/system/ntpdate.service"

SRC_URI += " \
	file://ntpdate.service \
        file://ntpd.service \
"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/ntpdate.service ${D}${systemd_unitdir}/system/
	install -m 0644 ${WORKDIR}/ntpd.service ${D}${systemd_unitdir}/system/
}
