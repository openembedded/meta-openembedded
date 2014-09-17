# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SYSTEMD_PACKAGES += "dhcp-client"
SYSTEMD_SERVICE_dhcp-client = "dhclient.service"
SYSTEMD_AUTO_ENABLE_dhcp-client = "disable"

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

SRC_URI += "file://dhclient.service"

do_install_append() {
    install -m 0644 ${WORKDIR}/dhclient.service ${D}${systemd_unitdir}/system
}

