# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit systemd

PRINC := "${@int(PRINC) + 3}"

SYSTEMD_PACKAGES = "dhcp-server dhcp-relay dhcp-client"
SYSTEMD_SERVICE_dhcp-server = "dhcpd.service"
SYSTEMD_SERVICE_dhcp-relay = "dhcrelay.service"
SYSTEMD_SERVICE_dhcp-client = "dhclient.service"
SYSTEMD_AUTO_ENABLE_dhcp-client = "disable"

FILES_dhcp-server += "${systemd_unitdir}/system/dhcpd.service"
FILES_dhcp-relay += "${systemd_unitdir}/system/dhrelay.service"
FILES_dhcp-client += "${systemd_unitdir}/system/dhclient.service"
RPROVIDES_dhcp-server += "dhcp-server-systemd"
RREPLACES_dhcp-server += "dhcp-server-systemd"
RCONFLICTS_dhcp-server += "dhcp-server-systemd"
RPROVIDES_dhcp-relay += "dhcp-relay-systemd"
RREPLACES_dhcp-relay += "dhcp-relay-systemd"
RCONFLICTS_dhcp-relay += "dhcp-relay-systemd"
RPROVIDES_dhcp-client += "dhcp-client-systemd"
RREPLACES_dhcp-client += "dhcp-client-systemd"
RCONFLICTS_dhcp-client += "dhcp-client-systemd"

SRC_URI += "file://dhcpd.service \
            file://dhclient.service \
            file://dhcrelay.service \
"
do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/dhcpd.service ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/dhclient.service ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/dhcrelay.service ${D}${systemd_unitdir}/system
}

