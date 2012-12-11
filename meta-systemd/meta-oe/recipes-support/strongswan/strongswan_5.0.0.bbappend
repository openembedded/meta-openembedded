inherit systemd

PRINC := "${@int(PRINC) + 1}"

SYSTEMD_UNITDIR = "${systemd_unitdir}/system"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "${PN}.service"
