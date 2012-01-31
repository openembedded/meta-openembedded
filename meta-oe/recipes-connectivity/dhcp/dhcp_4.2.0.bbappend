# look for files in the layer first
FILESEXTRAPATHS := "${THISDIR}/files"

inherit systemd

PRINC := "${@int(PRINC) + 1}"

SYSTEMD_PACKAGES = "dhcp-server-systemd"
SYSTEMD_SERVICE_dhcp-server-systemd = "dhcpd.service"

SRC_URI += "file://dhcpd.service"

do_install_append() {
    install -d ${D}${base_libdir}/systemd/system
    install -m 644 ${WORKDIR}/dhcpd.service ${D}${base_libdir}/systemd/system
}

PACKAGES =+ "dhcp-server-systemd"

FILES_dhcp-server-systemd += "${base_libdir}/systemd"
RDEPENDS_dhcp-server-systemd += "dhcp-server"
