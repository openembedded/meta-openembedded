# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit systemd

PRINC := "${@int(PRINC) + 1}"

SYSTEMD_PACKAGES = "dhcp-server-systemd dhcp-relay-systemd dhcp-client-systemd"
SYSTEMD_SERVICE_dhcp-server-systemd = "dhcpd.service"
SYSTEMD_SERVICE_dhcp-relay-systemd = "dhcrelay.service"
SYSTEMD_SERVICE_dhcp-client-systemd = "dhclient.service"

SRC_URI += "file://dhcpd.service \
            file://dhclient.service \
            file://dhcrelay.service \
           "
