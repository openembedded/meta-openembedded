# look for files in the layer first
FILESEXTRAPATHS := "${THISDIR}/files"

inherit systemd

PRINC := "${@int(PRINC) + 1}"

SYSTEMD_PACKAGES = "dhcp-server-systemd dhcp-relay-systemd"
SYSTEMD_SERVICE_dhcp-server-systemd = "dhcpd.service"
SYSTEMD_SERVICE_dhcp-relay-systemd = "dhcrelay.service"

SRC_URI += "file://dhcpd.service \
            file://dhcrelay.service"
