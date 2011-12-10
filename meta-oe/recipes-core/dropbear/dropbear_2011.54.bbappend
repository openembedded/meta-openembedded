inherit systemd

PRINC := "${@int(PRINC) + 1}"

# look for files in the layer first
FILESEXTRAPATHS := "${THISDIR}/${PN}"

SRC_URI += "file://dropbearkey.service \
            file://dropbear@.service \
            file://dropbear.socket"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "dropbear.socket"

do_install_append() {
	install -d ${D}${base_libdir}/systemd/system
	install -m 0644 ${WORKDIR}/dropbearkey.service ${D}${base_libdir}/systemd/system/
	install -m 0644 ${WORKDIR}/dropbear@.service ${D}${base_libdir}/systemd/system/
	install -m 0644 ${WORKDIR}/dropbear.socket ${D}${base_libdir}/systemd/system/
	ln -sf /dev/null ${D}${base_libdir}/systemd/system/dropbear.service
}

PACKAGES += "${PN}-systemd"

RDEPENDS_${PN}-systemd += "dropbear"
FILES_${PN}-systemd = "${base_libdir}/systemd"
