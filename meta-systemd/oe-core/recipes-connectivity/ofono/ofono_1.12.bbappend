PRINC := "${@int(PRINC) + 2}"

inherit systemd

RPROVIDES_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "ofono.service"
SYSTEMD_AUTO_ENABLE = "disable"
