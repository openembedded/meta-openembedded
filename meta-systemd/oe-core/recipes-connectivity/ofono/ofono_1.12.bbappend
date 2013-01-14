PRINC := "${@int(PRINC) + 1}"

inherit systemd

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "ofono.service"
SYSTEMD_AUTO_ENABLE = "disable"
