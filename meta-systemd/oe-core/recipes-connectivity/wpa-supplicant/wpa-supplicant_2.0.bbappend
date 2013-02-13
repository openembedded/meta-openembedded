PRINC := "${@int(PRINC) + 3}"

RPROVIDES_${PN} += "${PN}-systemd"
