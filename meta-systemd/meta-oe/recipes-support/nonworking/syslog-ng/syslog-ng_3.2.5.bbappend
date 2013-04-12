inherit systemd

PRINC := "${@int(PRINC) + 1}"

EXTRA_OECONF += " \
  --enable-systemd \
  --with-systemdsystemunitdir=${systemd_unitdir}/system \
"

RPROVIDES_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "${PN}.service"
