PRINC := "${@int(PRINC) + 4}"

RPROVIDES_${PN} += "${PN}-systemd"
