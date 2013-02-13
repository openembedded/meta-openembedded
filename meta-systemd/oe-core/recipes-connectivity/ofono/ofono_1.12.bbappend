PRINC := "${@int(PRINC) + 1}"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
RPROVIDES_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "ofono.service"
SYSTEMD_AUTO_ENABLE = "disable"
