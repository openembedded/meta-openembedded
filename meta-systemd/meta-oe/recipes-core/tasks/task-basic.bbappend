PRINC := "${@int(PRINC) + 1}"

RDEPENDS_${PN} += "\
	systemd-compat-units \
	avahi-systemd \
"
