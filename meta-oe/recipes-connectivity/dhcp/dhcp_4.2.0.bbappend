# look for files in the layer first
FILESEXTRAPATHS := "${THISDIR}/files"

inherit systemd

PRINC := "${@int(PRINC) + 2}"

SYSTEMD_PACKAGES = "dhcp-server-systemd"
SYSTEMD_SERVICE_dhcp-server-systemd = "dhcpd.service"

SRC_URI += "file://dhcpd.service"
