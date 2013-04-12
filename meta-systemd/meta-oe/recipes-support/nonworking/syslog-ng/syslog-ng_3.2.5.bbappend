inherit systemd

PRINC := "${@int(PRINC) + 2}"

EXTRA_OECONF += " \
  --enable-systemd \
  --with-systemdsystemunitdir=${systemd_unitdir}/system \
"

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "${PN}.service"
