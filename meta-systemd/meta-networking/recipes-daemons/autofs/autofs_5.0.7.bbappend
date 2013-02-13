inherit systemd

PRINC := "${@int(PRINC) + 1}"

SYSTEMD_PACKAGES = "${PN}"
RPROVIDES_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "autofs.service"
