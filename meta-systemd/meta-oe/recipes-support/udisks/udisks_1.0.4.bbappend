inherit systemd

PRINC := "${@int(PRINC) + 3}"

# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEPENDS += "systemd"

SRC_URI += "file://add-systemd-support.patch"

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "udisks-daemon.service"
SYSTEMD_AUTO_ENABLE = "disable"
