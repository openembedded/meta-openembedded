inherit systemd

PRINC := "${@int(PRINC) + 1}"

# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEPENDS += "systemd"

SRC_URI += "file://add-systemd-support.patch"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "udisks-daemon.service"
