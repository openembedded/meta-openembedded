inherit systemd

PRINC := "${@int(PRINC) + 1}"

RPROVIDES_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "pcscd.socket"
