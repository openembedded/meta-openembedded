PRINC := "${@int(PRINC) + 1}"

inherit systemd

EXTRA_OECONF += "--with-systemdsystemunitdir=${systemd_unitdir}/system"

do_install_append() {
    sed -i \
        's|^ExecStart=.*|ExecStart=${bindir}/mpd --no-daemon|' \
        ${D}/${systemd_unitdir}/system/mpd.service
}

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "mpd.service"
