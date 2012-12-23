# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS += "systemd"

PRINC := "${@int(PRINC) + 1}"

SRC_URI += "file://0001-Add-shutdown-reboot-functionality-for-systemd-bug-87.patch"
