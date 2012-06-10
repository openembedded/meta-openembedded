PRINC := "${@int(PRINC) + 1}"

inherit systemd

DEPENDS += "systemd"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE = "NetworkManager.service"
SYSTEMD_UNITDIR = "${systemd_unitdir}/system"

# NetworkManager-wait-online is not catched by systemd.bbclass
FILES_${PN} += " \
                ${systemd_unitdir}/system/NetworkManager-wait-online.service \
"
