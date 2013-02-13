inherit systemd

PRINC := "${@int(PRINC) + 2}"

# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEPENDS += "systemd"

SRC_URI += "file://add-systemd-support.patch"

SYSTEMD_PACKAGES = "${PN}"
RPROVIDES_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "udisks-daemon.service"
SYSTEMD_AUTO_ENABLE = "disable"
