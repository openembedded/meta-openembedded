PRINC := "${@int(PRINC) + 2}"

RDEPENDS_${PN} += "\
	systemd-compat-units \
	avahi-systemd \
"
