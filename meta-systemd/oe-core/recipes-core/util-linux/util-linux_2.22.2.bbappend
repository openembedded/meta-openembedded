inherit systemd

PRINC := "${@int(PRINC) + 1}"

SYSTEMD_UNITDIR = "${systemd_unitdir}/system"
EXTRA_OECONF += "--enable-socket-activation --with-systemdsystemunitdir=${SYSTEMD_UNITDIR}"
SYSTEMD_PACKAGES = "${PN}"
RPROVIDES_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "uuidd.socket"
