inherit systemd

PRINC := "${@int(PRINC) + 1}"

# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://domainname.service"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "domainname.service"

