inherit systemd

EXTRA_OECONF += "--with-systemdunitdir=${systemd_unitdir}/system/"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "connman.service"
