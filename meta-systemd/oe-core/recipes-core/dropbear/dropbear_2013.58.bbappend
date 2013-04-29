inherit systemd

PRINC := "${@int(PRINC) + 4}"

# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://dropbearkey.service \
            file://dropbear@.service \
            file://dropbear.socket"

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "dropbear.socket"

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/dropbearkey.service ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/dropbear@.service ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/dropbear.socket ${D}${systemd_unitdir}/system
    ln -sf /dev/null ${D}${systemd_unitdir}/system/dropbear.service
}
