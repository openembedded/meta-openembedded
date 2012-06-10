inherit systemd

PRINC := "${@int(PRINC) + 3}"

# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://dropbearkey.service \
            file://dropbear@.service \
            file://dropbear.socket"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "dropbear.socket"

do_install_append() {
	ln -sf /dev/null ${D}${systemd_unitdir}/system/dropbear.service
}
