inherit systemd

PRINC := "${@int(PRINC) + 1}"

# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://domainname.service"

SYSTEMD_PACKAGES = "${PN}"
RPROVIDES_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "domainname.service"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/domainname.service ${D}${systemd_unitdir}/system
}

