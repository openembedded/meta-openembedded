PRINC := "${@int(PRINC) + 2}"

inherit systemd

EXTRA_OECONF += "--with-systemdsystemunitdir=${systemd_unitdir}/system"

do_install_append() {
    sed -i \
        's|^ExecStart=.*|ExecStart=${bindir}/mpd --no-daemon|' \
        ${D}/${systemd_unitdir}/system/mpd.service
}

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "mpd.service"
