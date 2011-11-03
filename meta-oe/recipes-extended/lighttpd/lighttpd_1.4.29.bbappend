FILESEXTRAPATHS := "${THISDIR}/${PN}"
 
PRINC = "1"

SRC_URI += "file://lighttpd.service"

do_install_append() {
	install -d ${D}${base_libdir}/systemd/systemd/
	install -m 0644 ${WORKDIR}/lighttpd.service ${D}${base_libdir}/systemd/systemd/

	install -d ${D}${base_libdir}/systemd/systemd/multi-user.target.wants
	ln -sf ../lighttpd.service ${D}${base_libdir}/systemd/systemd/multi-user.target.wants
}

FILES_${PN} += "${base_libdir}/systemd"

