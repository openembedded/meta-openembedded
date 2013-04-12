inherit systemd

PRINC := "${@int(PRINC) + 2}"

SYSTEMD_UNITDIR = "${systemd_unitdir}/system"
EXTRA_OECONF += "--enable-socket-activation --with-systemdsystemunitdir=${SYSTEMD_UNITDIR}"
RPROVIDES_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "uuidd.socket"
