PRINC := "${@int(PRINC) + 1}"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "connman.service"
