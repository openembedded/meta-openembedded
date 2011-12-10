inherit systemd

EXTRA_OECONF += "--with-systemdunitdir=${base_libdir}/systemd/system/"

PRINC := "${@int(PRINC) + 1}"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "connman.service"

# systemd files
PACKAGES =+ "${PN}-systemd"
FILES_${PN}-systemd += "${base_libdir}/systemd"
RDEPENDS_${PN}-systemd += "${PN}"
